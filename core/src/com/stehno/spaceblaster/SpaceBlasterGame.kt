package com.stehno.spaceblaster

import com.badlogic.gdx.Application
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Logger
import com.stehno.spaceblaster.screen.LoadingScreen

class SpaceBlasterGame : Game() {

    val assetManager = AssetManager()
    val debug = true // TODO: something better

    lateinit var batch: SpriteBatch
        private set

    override fun create() {
        if (debug) {
            Gdx.app.logLevel = Application.LOG_DEBUG
            assetManager.logger.level = Logger.DEBUG
        } else {
            Gdx.app.logLevel = Application.LOG_ERROR
            assetManager.logger.level = Logger.ERROR
        }

        batch = SpriteBatch()

        setScreen(LoadingScreen(this))
    }

    override fun dispose() {
        assetManager.dispose()
        batch.dispose()
    }
}
