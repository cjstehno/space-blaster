package com.stehno.spaceblaster.system

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Input
import com.stehno.spaceblaster.component.Mappers
import com.stehno.spaceblaster.component.MovementComponent
import com.stehno.spaceblaster.component.PlayerComponent
import com.stehno.spaceblaster.config.GameConfig
import com.stehno.spaceblaster.util.isKeyPressed

/**
 * Handles player controls.
 */
class PlayerSystem : IteratingSystem(FAMILY) {

    companion object {
        private val FAMILY = Family.all(PlayerComponent::class.java, MovementComponent::class.java).get()
    }

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val movement = Mappers.MOVEMENT[entity].apply {
            xSpeed = 0f
        }

        when {
            Input.Keys.RIGHT.isKeyPressed() -> movement.xSpeed = GameConfig.PLAYER_MAX_X_SPEED
            Input.Keys.LEFT.isKeyPressed() -> movement.xSpeed = -GameConfig.PLAYER_MAX_X_SPEED
        }
    }
}
