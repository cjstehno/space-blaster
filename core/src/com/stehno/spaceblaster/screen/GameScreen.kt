package com.stehno.spaceblaster.screen

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.stehno.spaceblaster.EntityFactory
import com.stehno.spaceblaster.GameManager
import com.stehno.spaceblaster.SpaceBlasterGame
import com.stehno.spaceblaster.asset.AssetDescriptors
import com.stehno.spaceblaster.config.GameConfig
import com.stehno.spaceblaster.system.*
import com.stehno.spaceblaster.util.clearScreen

class GameScreen(val game: SpaceBlasterGame) : ScreenAdapter() {

    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var hudViewport: Viewport
    private lateinit var renderer: ShapeRenderer
    private lateinit var engine: PooledEngine
    private lateinit var factory: EntityFactory
    private lateinit var hitSound: Sound

    private val assetManager = game.assetManager

    override fun show() {
        camera = OrthographicCamera()
        viewport = FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera)
        renderer = ShapeRenderer()

        hudViewport = FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT)

        engine = PooledEngine()
        factory = EntityFactory(engine, assetManager)

        hitSound = assetManager[AssetDescriptors.ASTEROID_HIT_SOUND]

        engine.addSystem(DebugCameraSystem(camera, GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y))

        engine.addSystem(PlayerSystem())
        engine.addSystem(MovementSystem())
        engine.addSystem(WorldWrapSystem(viewport))
        engine.addSystem(BoundsSystem())
        engine.addSystem(AsteroidSpawnSystem(factory))
        engine.addSystem(CleanupSystem())

        val listener = object : CollisionListener {
            override fun hitObstacle() {
                GameManager.INSTANCE.decrementLives()
                hitSound.play()

                if (GameManager.INSTANCE.isGameOver()) {
                    GameManager.INSTANCE.updateHighScore()
                } else {
                    engine.removeAllEntities()
                    factory.addPlayer()
                }
            }
        }

        engine.addSystem(CollisionSystem(listener))
        engine.addSystem(ScoreSystem())

        engine.addSystem(RenderSystem(viewport, game.batch))

        engine.addSystem(GridRenderSystem(viewport, renderer))
        engine.addSystem(DebugRenderSystem(viewport, renderer))

        engine.addSystem(HudRenderSystem(hudViewport, game.batch, assetManager[AssetDescriptors.FONT]))

        factory.addBackground()
        factory.addPlayer()
    }

    override fun render(delta: Float) {
        clearScreen()
        engine.update(delta)

        if (GameManager.INSTANCE.isGameOver()) {
            GameManager.INSTANCE.reset()
//    FIXME:         game.screen = MenuScreen(game)
        }
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
        hudViewport.update(width, height, true)
    }

    override fun hide() {
        dispose()
    }

    override fun dispose() {
        renderer.dispose()
    }
}