package com.stehno.spaceblaster.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntitySystem
import com.badlogic.ashley.core.Family
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.Viewport
import com.stehno.spaceblaster.component.DimensionComponent
import com.stehno.spaceblaster.component.Mappers.DIMENSION
import com.stehno.spaceblaster.component.Mappers.POSITION
import com.stehno.spaceblaster.component.Mappers.TEXTURE
import com.stehno.spaceblaster.component.PositionComponent
import com.stehno.spaceblaster.component.TextureComponent
import com.stehno.spaceblaster.util.GdxArray
import com.stehno.spaceblaster.util.use

/**
 * Logic for rendering game play.
 */
class RenderSystem(private val viewport: Viewport,
                   private val batch: SpriteBatch) : EntitySystem() {

    companion object {
        private val FAMILY = Family.all(TextureComponent::class.java, PositionComponent::class.java, DimensionComponent::class.java).get()
    }

    private val renderQueue = GdxArray<Entity>()

    override fun update(deltaTime: Float) {
        val entities = engine.getEntitiesFor(FAMILY)
        renderQueue.addAll(*entities.toArray(Entity::class.java))

        viewport.apply()
        batch.projectionMatrix = viewport.camera.combined
        batch.use { draw() }

        renderQueue.clear()
    }

    private fun draw() {
        for (entity in renderQueue) {
            val position = POSITION[entity]
            val dimension = DIMENSION[entity]
            val texture = TEXTURE[entity]

            batch.draw(texture.region, position.x, position.y, dimension.width, dimension.height)
        }
    }
}

