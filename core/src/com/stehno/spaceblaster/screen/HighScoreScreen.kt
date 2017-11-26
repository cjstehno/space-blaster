package com.stehno.spaceblaster.screen


import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.stehno.spaceblaster.GameManager
import com.stehno.spaceblaster.SpaceBlasterGame
import com.stehno.spaceblaster.asset.AssetDescriptors
import com.stehno.spaceblaster.asset.RegionNames
import com.stehno.spaceblaster.util.get

class HighScoreScreen(private val game: SpaceBlasterGame) : MenuScreenBase(game) {

    override fun createUi(): Actor {
        val table = Table()

        val gamePlayAtlas = assetManager[AssetDescriptors.GAME_ATLAS]
        val skin = assetManager[AssetDescriptors.SKIN]

        table.background = TextureRegionDrawable(gamePlayAtlas[RegionNames.STARFIELD])

        val highScoreText = Label("HIGH SCORE", skin)

        val highScoreString = GameManager.INSTANCE.getHighScoreString()
        val highScoreLabel = Label(highScoreString, skin)

        val backButton = TextButton("BACK", skin).apply {
            addListener(object : ChangeListener() {
                override fun changed(event: ChangeEvent?, actor: Actor?) {
                    back()
                }
            })
        }

        // setup tables
        val contentTable = Table(skin)
        contentTable.defaults().pad(20f)
        contentTable.setBackground(RegionNames.PANEL)

        contentTable.add(highScoreText).row()
        contentTable.add(highScoreLabel).row()
        contentTable.add(backButton)

        contentTable.center()

        table.add(contentTable)
        table.center()
        table.setFillParent(true)
        table.pack()

        return table
    }

    private fun back() {
        game.screen = MenuScreen(game)
    }
}