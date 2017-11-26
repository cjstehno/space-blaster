package com.stehno.spaceblaster.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.stehno.spaceblaster.SpaceBlasterGame
import com.stehno.spaceblaster.asset.AssetDescriptors
import com.stehno.spaceblaster.asset.RegionNames
import com.stehno.spaceblaster.util.get

class MenuScreen(private val game: SpaceBlasterGame) : MenuScreenBase(game) {

    override fun createUi(): Actor {
        val table = Table()

        val gamePlayAtlas = assetManager[AssetDescriptors.GAME_ATLAS]
        val backgroundRegion = gamePlayAtlas[RegionNames.STARFIELD]
        table.background = TextureRegionDrawable(backgroundRegion)

        val skin = assetManager[AssetDescriptors.SKIN]

        val playButton = TextButton("PLAY", skin).apply {
            addListener(object : ChangeListener() {
                override fun changed(event: ChangeEvent?, actor: Actor?) {
                    play()
                }
            })
        }

        val highScoreButton = TextButton("HIGH SCORE", skin).apply {
            addListener(object : ChangeListener() {
                override fun changed(event: ChangeEvent?, actor: Actor?) {
                    showHighScore()
                }
            })
        }

        val optionsButton = TextButton("OPTIONS", skin).apply {
            addListener(object : ChangeListener() {
                override fun changed(event: ChangeEvent?, actor: Actor?) {
                    showOptions()
                }
            })
        }

        val quitButton = TextButton("QUIT", skin).apply {
            addListener(object : ChangeListener() {
                override fun changed(event: ChangeEvent?, actor: Actor?) {
                    quit()
                }
            })
        }

        // setup table
        val buttonTable = Table(skin)
        buttonTable.defaults().pad(20f)
        buttonTable.setBackground(RegionNames.PANEL)
        buttonTable.add(playButton).row()
        buttonTable.add(highScoreButton).row()
        buttonTable.add(optionsButton).row()
        buttonTable.add(quitButton)
        buttonTable.center()
        table.add(buttonTable)

        table.center()
        table.setFillParent(true)
        table.pack()

        return table
    }

    private fun play() {
        game.screen = GameScreen(game)
    }

    private fun showHighScore() {
        game.screen = HighScoreScreen(game)
    }

    private fun showOptions() {
        game.screen = OptionsScreen(game)
    }

    private fun quit() {
        Gdx.app.exit()
    }
}