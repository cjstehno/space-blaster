package com.stehno.spaceblaster.util.debug

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.stehno.spaceblaster.util.logger

class DebugCameraController(private val startX: Float, private val startY: Float) {

    companion object {
        @JvmStatic
        private val log = logger<DebugCameraController>()
    }

    private val position = Vector2(startX, startY)
    private val config = DebugCameraConfig()

    private var zoom = 1f
        set(value) {
            field = MathUtils.clamp(value, config.maxZoomIn, config.maxZoomOut)
        }

    fun applyTo(camera: OrthographicCamera) {
        camera.position.set(position, 0f)
        camera.zoom = zoom
        camera.update()
    }

    fun handleDebugInput() {
        val delta = Gdx.graphics.deltaTime
        val moveSpeed = config.moveSpeed * delta
        val zoomSpeed = config.zoomSpeed * delta

        when {
            config.isLeftPressed() -> moveLeft(moveSpeed)
            config.isRightPressed() -> moveRight(moveSpeed)
            config.isUpPressed() -> moveUp(moveSpeed)
            config.isDownPressed() -> moveDown(moveSpeed)

            config.isZoomInPressed() -> zoomIn(zoomSpeed)
            config.isZoomOutPressed() -> zoomOut(zoomSpeed)

            config.isResetPressed() -> reset()
            config.isLogPressed() -> log.debug("position=$position, zoom=$zoom")
        }
    }

    private fun zoomIn(speed: Float) {
        zoom += speed
    }

    private fun zoomOut(speed: Float) {
        zoom -= speed
    }

    private fun reset() {
        position.set(startX, startY)
        zoom = 1f
    }

    private fun moveDown(moveSpeed: Float) = moveCamera(0f, -moveSpeed)

    private fun moveUp(moveSpeed: Float) = moveCamera(0f, moveSpeed)

    private fun moveRight(moveSpeed: Float) = moveCamera(moveSpeed, 0f)

    private fun moveLeft(moveSpeed: Float) = moveCamera(-moveSpeed, 0f)

    private fun moveCamera(xSpeed: Float, ySpeed: Float) = setPosition(position.x + xSpeed, position.y + ySpeed)

    private fun setPosition(x: Float, y: Float) = position.set(x, y)
}