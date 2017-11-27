package com.stehno.spaceblaster.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.Viewport
import com.stehno.spaceblaster.component.AnimatedComponent
import com.stehno.spaceblaster.component.DimensionComponent
import com.stehno.spaceblaster.component.Mappers
import com.stehno.spaceblaster.component.Mappers.ANIMATION
import com.stehno.spaceblaster.component.PositionComponent
import com.stehno.spaceblaster.util.use

/**
 * Logic used to render sprite animations
 */
class AnimationSystem(private val viewport: Viewport,
                      private val batch: SpriteBatch) : IteratingSystem(FAMILY) {

    companion object {
        private val FAMILY = Family.all(
            AnimatedComponent::class.java, PositionComponent::class.java, DimensionComponent::class.java
        ).get()
    }

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val animation = ANIMATION[entity]
        val position = Mappers.POSITION[entity]
        val dimension = Mappers.DIMENSION[entity]

        viewport.apply()
        batch.projectionMatrix = viewport.camera.combined

        val currentFrame = animation.animation.getKeyFrame(deltaTime, true)
        batch.use {
            batch.draw(currentFrame, position.x, position.y, dimension.width, dimension.height)
        }
    }
}
