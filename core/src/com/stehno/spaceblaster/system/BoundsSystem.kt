package com.stehno.spaceblaster.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.stehno.spaceblaster.component.BoundsComponent
import com.stehno.spaceblaster.component.DimensionComponent
import com.stehno.spaceblaster.component.Mappers.BOUNDS
import com.stehno.spaceblaster.component.Mappers.DIMENSION
import com.stehno.spaceblaster.component.Mappers.POSITION
import com.stehno.spaceblaster.component.PositionComponent

/**
 * Handles the updating of the bounds based on position
 */
class BoundsSystem : IteratingSystem(FAMILY) {

    companion object {
        private val FAMILY = Family.all(
            PositionComponent::class.java, BoundsComponent::class.java, DimensionComponent::class.java
        ).get()
    }

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val bounds = BOUNDS[entity]
        val position = POSITION[entity]
        val dimension = DIMENSION[entity]

        bounds.bounds.x = position.x + dimension.width / 2f
        bounds.bounds.y = position.y + dimension.height / 2f
    }
}