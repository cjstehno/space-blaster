package com.stehno.spaceblaster.system

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.Viewport
import com.stehno.spaceblaster.util.use

/**
 * Renders debug grid lines in viewport.
 */
class GridRenderSystem(private val viewport: Viewport,
                       private val renderer: ShapeRenderer) : EntitySystem() {

    override fun update(deltaTime: Float) {
        val oldColor = renderer.color.cpy()

        val doubleWorldWidth = viewport.worldWidth * 2
        val doubleWorldHeight = viewport.worldHeight * 2

        viewport.apply()
        renderer.projectionMatrix = viewport.camera.combined
        renderer.use {
            renderer.color = Color.WHITE

            // draw vertical lines
            var x = -doubleWorldWidth
            while (x < doubleWorldWidth) {
                renderer.line(x, -doubleWorldHeight, x, doubleWorldHeight)
                x += 1
            }

            // draw horizontal lines
            var y = -doubleWorldHeight
            while (y < doubleWorldHeight) {
                renderer.line(-doubleWorldWidth, y, doubleWorldWidth, y)
                y += 1
            }

            // draw 0/0 lines
            renderer.color = Color.RED
            renderer.line(0.01f, -doubleWorldHeight, 0.01f, doubleWorldHeight)
            renderer.line(-doubleWorldWidth, 0.01f, doubleWorldWidth, 0.01f)

            // draw world bounds
            renderer.color = Color.GREEN
            renderer.line(0f, viewport.worldHeight, viewport.worldWidth, viewport.worldHeight)
            renderer.line(viewport.worldWidth, 0f, viewport.worldWidth, viewport.worldHeight)
        }

        renderer.color = oldColor
    }
}
