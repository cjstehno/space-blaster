package com.stehno.spaceblaster.screen


import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.stehno.spaceblaster.GameManager
import com.stehno.spaceblaster.SpaceBlasterGame
import com.stehno.spaceblaster.asset.AssetDescriptors
import com.stehno.spaceblaster.asset.RegionNames
import com.stehno.spaceblaster.config.DifficultyLevel
import com.stehno.spaceblaster.util.get

class OptionsScreen(private val game: SpaceBlasterGame) : MenuScreenBase(game) {

    private lateinit var checkBoxGroup: ButtonGroup<CheckBox>
    private lateinit var easy: CheckBox
    private lateinit var medium: CheckBox
    private lateinit var hard: CheckBox

    override fun createUi(): Actor {
        val table = Table()
        table.defaults().pad(15f)

        val gamePlayAtlas = assetManager[AssetDescriptors.GAME_ATLAS]
        val uiskin = assetManager[AssetDescriptors.SKIN]

        val backgroundRegion = gamePlayAtlas[RegionNames.STARFIELD]
        table.background = TextureRegionDrawable(backgroundRegion)

        val label = Label("DIFFICULTY", uiskin)

        easy = checkBox(DifficultyLevel.EASY.name, uiskin)
        medium = checkBox(DifficultyLevel.MEDIUM.name, uiskin)
        hard = checkBox(DifficultyLevel.HARD.name, uiskin)

        checkBoxGroup = ButtonGroup(easy, medium, hard)

        val difficultyLevel = GameManager.INSTANCE.difficultyLevel
        checkBoxGroup.setChecked(difficultyLevel.name)

        val backButton = TextButton("BACK", uiskin).apply {
            addListener(object : ChangeListener() {
                override fun changed(event: ChangeEvent?, actor: Actor?) {
                    back()
                }
            })
        }

        val listener = object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                difficultyChanged()
            }
        }

        easy.addListener(listener)
        medium.addListener(listener)
        hard.addListener(listener)

        val contentTable = Table(uiskin)
        contentTable.defaults().pad(10f)
        contentTable.setBackground(RegionNames.PANEL)
        contentTable.add(label).row()
        contentTable.add(easy).row()
        contentTable.add(medium).row()
        contentTable.add(hard).row()
        contentTable.add(backButton)

        table.add(contentTable)
        table.center()
        table.setFillParent(true)
        table.pack()

        return table
    }

    private fun checkBox(text: String, skin: Skin) = CheckBox(text, skin).apply {
        left().pad(8f)
        labelCell.pad(8f)
    }

    private fun back() {
        game.screen = MenuScreen(game)
    }

    private fun difficultyChanged() {
        when (checkBoxGroup.checked) {
            easy -> GameManager.INSTANCE.updateDifficulty(DifficultyLevel.EASY)
            medium -> GameManager.INSTANCE.updateDifficulty(DifficultyLevel.MEDIUM)
            hard -> GameManager.INSTANCE.updateDifficulty(DifficultyLevel.HARD)
        }
    }
}