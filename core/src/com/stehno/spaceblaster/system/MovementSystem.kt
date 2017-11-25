package com.stehno.spaceblaster.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.stehno.spaceblaster.component.Mappers
import com.stehno.spaceblaster.component.MovementComponent
import com.stehno.spaceblaster.component.PositionComponent

/**
 * Handles entity movement.
 */
class MovementSystem : IteratingSystem(FAMILY) {

    companion object {
        private val FAMILY = Family.all(PositionComponent::class.java, MovementComponent::class.java).get()
    }

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val position = Mappers.POSITION[entity]
        val movement = Mappers.MOVEMENT[entity]

        position.x += movement.xSpeed
        position.y += movement.ySpeed
    }
}