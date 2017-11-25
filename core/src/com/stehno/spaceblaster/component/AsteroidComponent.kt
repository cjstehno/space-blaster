package com.stehno.spaceblaster.component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool

class AsteroidComponent(var hit: Boolean = false) : Component, Pool.Poolable {

    override fun reset() {
        hit = false
    }
}