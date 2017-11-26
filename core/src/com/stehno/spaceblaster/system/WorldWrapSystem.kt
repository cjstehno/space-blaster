package com.stehno.spaceblaster.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.math.MathUtils.clamp
import com.badlogic.gdx.utils.viewport.Viewport
import com.stehno.spaceblaster.component.DimensionComponent
import com.stehno.spaceblaster.component.Mappers.DIMENSION
import com.stehno.spaceblaster.component.Mappers.POSITION
import com.stehno.spaceblaster.component.PositionComponent
import com.stehno.spaceblaster.component.WorldWrapComponent

/**
 * Logic to constrain entities to the world.
 */
class WorldWrapSystem(private val viewport: Viewport) : IteratingSystem(FAMILY) {

    companion object {
        private val FAMILY = Family.all(
            WorldWrapComponent::class.java, PositionComponent::class.java, DimensionComponent::class.java
        ).get()
    }

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val position = POSITION[entity]
        val dimension = DIMENSION[entity]

        position.x = clamp(position.x, 0f, viewport.worldWidth - dimension.width)
        position.y = clamp(position.y, 0f, viewport.worldHeight - dimension.height)
    }
}
