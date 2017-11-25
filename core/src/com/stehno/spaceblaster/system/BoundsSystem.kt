package com.stehno.spaceblaster.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.stehno.spaceblaster.component.BoundsComponent
import com.stehno.spaceblaster.component.Mappers
import com.stehno.spaceblaster.component.PositionComponent

/**
 * Handles the updating of the bounds based on position
 */
class BoundsSystem : IteratingSystem(FAMILY) {

    companion object {
        private val FAMILY = Family.all(PositionComponent::class.java, BoundsComponent::class.java).get()
    }

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val bounds = Mappers.BOUNDS[entity]
        val position = Mappers.POSITION[entity]

        bounds.bounds.x = position.x
        bounds.bounds.y = position.y
    }
}