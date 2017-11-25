package com.stehno.spaceblaster.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.stehno.spaceblaster.component.CleanupComponent
import com.stehno.spaceblaster.component.Mappers.POSITION
import com.stehno.spaceblaster.component.PositionComponent
import com.stehno.spaceblaster.config.GameConfig

/**
 * Logic used to cleanup entities.
 */
class CleanupSystem : IteratingSystem(FAMILY) {

    companion object {
        private val FAMILY = Family.all(CleanupComponent::class.java, PositionComponent::class.java).get()
    }

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val position = POSITION[entity]
        if (position.y < -GameConfig.ASTEROID_SIZE) {
            engine.removeEntity(entity)
        }
    }
}