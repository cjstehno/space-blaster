package com.stehno.spaceblaster

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.stehno.spaceblaster.config.DifficultyLevel
import com.stehno.spaceblaster.config.GameConfig

class GameManager private constructor(private val prefs: Preferences) {

    companion object {
        val INSTANCE = GameManager(Gdx.app.getPreferences(SpaceBlasterGame::class.java.simpleName))

        private const val DIFFICULTY_KEY = "difficulty"
        private const val HIGH_SCORE_KEY = "highscore"
    }

    private var highscore = prefs.getInteger(HIGH_SCORE_KEY, 0)

    var score = 0
        private set

    var difficultyLevel = DifficultyLevel.valueOf(prefs.getString(DIFFICULTY_KEY, DifficultyLevel.MEDIUM.name))
        private set

    var lives = GameConfig.LIVES_START
        private set

    fun updateDifficulty(newDifficultyLevel: DifficultyLevel) {
        if (difficultyLevel != newDifficultyLevel) {
            difficultyLevel = newDifficultyLevel
            prefs.putString(DIFFICULTY_KEY, difficultyLevel.name)
            prefs.flush()
        }
    }

    fun updateHighScore() {
        if (score < highscore) {
            return
        }

        highscore = score
        prefs.putInteger(HIGH_SCORE_KEY, highscore)
        prefs.flush()
    }

    fun getHighScoreString() = "$highscore"

    fun decrementLives() = lives--

    fun isGameOver() = lives <= 0

    fun reset() {
        lives = GameConfig.LIVES_START
        score = 0
    }

    fun updateScore(amount: Int) {
        score += amount
    }
}