package com.stehno.spaceblaster.system

import com.badlogic.ashley.systems.IntervalSystem
import com.badlogic.gdx.math.MathUtils
import com.stehno.spaceblaster.EntityFactory
import com.stehno.spaceblaster.config.GameConfig

/**
 * Logic for spawning asteroids.
 */
class AsteroidSpawnSystem(private val factory: EntityFactory) : IntervalSystem(GameConfig.ASTEROID_SPAWN_TIME) {

    override fun updateInterval() {
        val min = 0f
        val max = GameConfig.WORLD_WIDTH

        val asteroidX = MathUtils.random(min, max)
        val asteroidY = GameConfig.WORLD_HEIGHT

        factory.addAsteroid(asteroidX, asteroidY)
    }
}