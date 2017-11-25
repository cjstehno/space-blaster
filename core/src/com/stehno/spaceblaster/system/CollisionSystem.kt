package com.stehno.spaceblaster.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntitySystem
import com.badlogic.ashley.core.Family
import com.badlogic.gdx.math.Intersector.overlaps
import com.stehno.spaceblaster.component.AsteroidComponent
import com.stehno.spaceblaster.component.BoundsComponent
import com.stehno.spaceblaster.component.Mappers.ASTEROID
import com.stehno.spaceblaster.component.Mappers.BOUNDS
import com.stehno.spaceblaster.component.PlayerComponent

/**
 * Logic for collision handling.
 */
class CollisionSystem : EntitySystem() {

    companion object {
        private val PLAYER_FAMILY = Family.all(PlayerComponent::class.java, BoundsComponent::class.java).get()
        private val ASTEROID_FAMILY = Family.all(AsteroidComponent::class.java, BoundsComponent::class.java).get()
    }

    override fun update(deltaTime: Float) {
        for (playerEntity in engine.getEntitiesFor(PLAYER_FAMILY)) {
            for (asteroidEntity in engine.getEntitiesFor(ASTEROID_FAMILY)) {
                val asteroid = ASTEROID[asteroidEntity]

                if (asteroid.hit) {
                    continue
                }

                if (checkCollision(playerEntity, asteroidEntity)) {
                    asteroid.hit = true
                }
            }
        }
    }

    private fun checkCollision(player: Entity, asteroid: Entity) = overlaps(BOUNDS[player].bounds, BOUNDS[asteroid].bounds)
}