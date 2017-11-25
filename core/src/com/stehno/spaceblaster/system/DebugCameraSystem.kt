package com.stehno.spaceblaster.system

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.OrthographicCamera
import com.stehno.spaceblaster.util.debug.DebugCameraController

/**
 * Provides debug controls for camera positioning.
 */
class DebugCameraSystem(private val camera: OrthographicCamera,
                        startX: Float,
                        startY: Float) : EntitySystem() {

    private val cameraController = DebugCameraController(startX, startY)

    override fun update(deltaTime: Float) {
        cameraController.handleDebugInput()
        cameraController.applyTo(camera)
    }
}
