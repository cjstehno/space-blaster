package com.stehno.spaceblaster.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.stehno.spaceblaster.SpaceBlasterGame
import com.stehno.spaceblaster.config.GameConfig
import com.stehno.spaceblaster.util.clearScreen

abstract class MenuScreenBase(private val game: SpaceBlasterGame) : ScreenAdapter() {

    protected var assetManager = game.assetManager
    protected lateinit var viewport: Viewport
    protected lateinit var stage: Stage

    override fun show() {
        viewport = FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT)
        stage = Stage(viewport, game.batch)

        Gdx.input.inputProcessor = stage

        stage.addActor(createUi())
    }

    protected abstract fun createUi(): Actor

    override fun render(delta: Float) {
        clearScreen()

        stage.act()
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun hide() {
        dispose()
    }

    override fun dispose() {
        stage.dispose()
    }
}