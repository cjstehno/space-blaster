package com.stehno.spaceblaster.screen

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.stehno.spaceblaster.EntityFactory
import com.stehno.spaceblaster.SpaceBlasterGame
import com.stehno.spaceblaster.config.GameConfig
import com.stehno.spaceblaster.system.*
import com.stehno.spaceblaster.util.clearScreen

class GameScreen(val game: SpaceBlasterGame) : ScreenAdapter() {

    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var renderer: ShapeRenderer
    private lateinit var engine: PooledEngine
    private lateinit var factory: EntityFactory

    override fun show() {
        camera = OrthographicCamera()
        viewport = FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera)
        renderer = ShapeRenderer()

        engine = PooledEngine()
        factory = EntityFactory(engine)

        engine.addSystem(GridRenderSystem(viewport, renderer))
        engine.addSystem(DebugCameraSystem(camera, GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y))
        engine.addSystem(DebugRenderSystem(viewport, renderer))
        engine.addSystem(PlayerSystem())
        engine.addSystem(MovementSystem())
        engine.addSystem(BoundsSystem())

        factory.addPlayer()
    }

    override fun render(delta: Float) {
        clearScreen()
        engine.update(delta)
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun hide() {
        dispose()
    }

    override fun dispose() {
        renderer.dispose()
    }
}