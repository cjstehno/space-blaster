package com.stehno.spaceblaster

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.stehno.spaceblaster.config.DifficultyLevel

class GameManager private constructor(private val prefs: Preferences){

    companion object {
        val INSTANCE = GameManager(Gdx.app.getPreferences(SpaceBlasterGame::class.java.simpleName))

        private const val DIFFICULTY_KEY = "difficulty"
    }

    var difficultyLevel = DifficultyLevel.valueOf(prefs.getString(DIFFICULTY_KEY, DifficultyLevel.MEDIUM.name))
        private set

    fun updateDifficulty(newDifficultyLevel: DifficultyLevel) {
        if (difficultyLevel != newDifficultyLevel) {
            difficultyLevel = newDifficultyLevel
            prefs.putString(DIFFICULTY_KEY, difficultyLevel.name)
            prefs.flush()
        }
    }
}