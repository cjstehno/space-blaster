package com.stehno.spaceblaster

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.stehno.spaceblaster.config.DifficultyLevel
import com.stehno.spaceblaster.config.GameConfig

class GameManager private constructor(private val prefs: Preferences){

    companion object {
        val INSTANCE = GameManager(Gdx.app.getPreferences(SpaceBlasterGame::class.java.simpleName))

        private const val DIFFICULTY_KEY = "difficulty"
    }

    var difficultyLevel = DifficultyLevel.valueOf(prefs.getString(DIFFICULTY_KEY, DifficultyLevel.MEDIUM.name))
        private set

    var lives = GameConfig.LIVES_START
    var score = 0

    fun updateDifficulty(newDifficultyLevel: DifficultyLevel) {
        if (difficultyLevel != newDifficultyLevel) {
            difficultyLevel = newDifficultyLevel
            prefs.putString(DIFFICULTY_KEY, difficultyLevel.name)
            prefs.flush()
        }
    }
}