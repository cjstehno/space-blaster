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
        factory.addAsteroid(
            MathUtils.random(0f, GameConfig.WORLD_WIDTH - GameConfig.ASTEROID_SIZE),
            GameConfig.WORLD_HEIGHT
        )
    }
}