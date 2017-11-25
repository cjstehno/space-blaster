package com.stehno.spaceblaster.component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool

class MovementComponent(var xSpeed: Float = 0f, var ySpeed: Float = 0f) : Component, Pool.Poolable {

    override fun reset() {
        xSpeed = 0f
        ySpeed = 0f
    }
}