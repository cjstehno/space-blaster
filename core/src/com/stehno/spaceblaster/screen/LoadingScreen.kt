package com.stehno.spaceblaster.screen

import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.stehno.spaceblaster.SpaceBlasterGame
import com.stehno.spaceblaster.asset.AssetDescriptors
import com.stehno.spaceblaster.config.GameConfig
import com.stehno.spaceblaster.util.clearScreen
import com.stehno.spaceblaster.util.logger
import com.stehno.spaceblaster.util.use

class LoadingScreen(private val game: SpaceBlasterGame) : ScreenAdapter() {

    companion object {
        @JvmStatic
        private val log = logger<LoadingScreen>()

        private const val PROGRESS_BAR_WIDTH = GameConfig.HUD_WIDTH / 2f
        private const val PROGRESS_BAR_HEIGHT = 60f
    }

    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: Viewport
    private lateinit var renderer: ShapeRenderer

    private var assetManager = game.assetManager
    private var progress = 0f
    private var waitTime = 0.75f
    private var changeScreen = false

    override fun show() {
        camera = OrthographicCamera()
        viewport = FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT, camera)
        renderer = ShapeRenderer()

        assetManager.load(AssetDescriptors.FONT)
        assetManager.load(AssetDescriptors.ASTEROID_HIT_SOUND)
        assetManager.load(AssetDescriptors.GAME_ATLAS)
    }

    override fun render(delta: Float) {
        update(delta)

        clearScreen()

        viewport.apply()
        renderer.projectionMatrix = camera.combined

        renderer.use(Filled) {
            draw()
        }

        if (changeScreen) {
            game.screen = GameScreen(game)
        }
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

    private fun update(delta: Float) {
        // progress is between 0 and 1
        progress = assetManager.progress

        // update returns true when all assets are loaded
        if (assetManager.update()) {
            waitTime -= delta

            if (waitTime <= 0) {
                log.debug("assetManager.diagnostics=${assetManager.diagnostics}")
                changeScreen = true
            }
        }
    }

    private fun draw() {
        renderer.rect(
            (GameConfig.HUD_WIDTH - PROGRESS_BAR_WIDTH) / 2f,
            (GameConfig.HUD_HEIGHT - PROGRESS_BAR_HEIGHT) / 2f,
            progress * PROGRESS_BAR_WIDTH,
            PROGRESS_BAR_HEIGHT
        )
    }
}