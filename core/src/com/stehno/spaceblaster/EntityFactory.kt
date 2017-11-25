package com.stehno.spaceblaster

import com.badlogic.ashley.core.PooledEngine
import com.stehno.spaceblaster.component.*
import com.stehno.spaceblaster.config.GameConfig

class EntityFactory(private val engine: PooledEngine) {

    fun addPlayer() {
        val startX = GameConfig.WORLD_CENTER_X
        val startY = 1f

        val bounds = engine.createComponent(BoundsComponent::class.java).apply {
            bounds.set(startX, startY, GameConfig.PLAYER_SIZE / 2f)
        }

        val position = engine.createComponent(PositionComponent::class.java).apply {
            x = startX
            y = startY
        }

        val player = engine.createComponent(PlayerComponent::class.java)

        val movement = engine.createComponent(MovementComponent::class.java)

        val worldWrap = engine.createComponent(WorldWrapComponent::class.java)

        val entity = engine.createEntity()
            .add(bounds)
            .add(position)
            .add(movement)
            .add(player)
            .add(worldWrap)

        engine.addEntity(entity)
    }

    fun addAsteroid(startX: Float, startY: Float) {
        val bounds = engine.createComponent(BoundsComponent::class.java).apply {
            bounds.set(startX, startY, GameConfig.ASTEROID_BOUNDS_RADIUS)
        }

        val movement = engine.createComponent(MovementComponent::class.java).apply {
            ySpeed = -GameManager.INSTANCE.difficultyLevel.speed
        }

        val position = engine.createComponent(PositionComponent::class.java).apply {
            x = startX
            y = startY
        }

        val cleanup = engine.createComponent(CleanupComponent::class.java)

        val asteroid = engine.createComponent(AsteroidComponent::class.java)

        val entity = engine.createEntity()
            .add(bounds)
            .add(movement)
            .add(position)
            .add(cleanup)
            .add(asteroid)

        engine.addEntity(entity)
    }
}