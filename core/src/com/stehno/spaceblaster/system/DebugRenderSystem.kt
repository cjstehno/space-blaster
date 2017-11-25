package com.stehno.spaceblaster.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.Viewport
import com.stehno.spaceblaster.component.BoundsComponent
import com.stehno.spaceblaster.component.Mappers
import com.stehno.spaceblaster.util.logger
import com.stehno.spaceblaster.util.use

/**
 * Renders debugging objects.
 */
class DebugRenderSystem(private val viewport: Viewport,
                        private val renderer: ShapeRenderer) : IteratingSystem(FAMILY) {

    companion object {
        @JvmStatic
        private val log = logger<DebugRenderSystem>()

        private val FAMILY = Family.all(BoundsComponent::class.java).get()
    }

    override fun update(deltaTime: Float) {
        val oldColor = renderer.color.cpy()

        viewport.apply()
        renderer.projectionMatrix = viewport.camera.combined

        renderer.use {
            renderer.color = Color.RED
            super.update(deltaTime)
        }

        renderer.color = oldColor
    }

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val bc = Mappers.BOUNDS[entity]
        renderer.circle(bc.bounds.x, bc.bounds.y, bc.bounds.radius, 30)
    }
}