package com.stehno.spaceblaster.system

import com.badlogic.ashley.systems.IntervalSystem
import com.badlogic.gdx.math.MathUtils
import com.stehno.spaceblaster.GameManager
import com.stehno.spaceblaster.config.GameConfig

/**
 * Logic for updating the score.
 */
class ScoreSystem : IntervalSystem(GameConfig.SCORE_MAX_TIME) {

    override fun updateInterval() {
        GameManager.INSTANCE.updateScore(MathUtils.random(1, 5))
    }
}