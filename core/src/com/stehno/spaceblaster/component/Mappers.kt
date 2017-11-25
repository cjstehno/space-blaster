package com.stehno.spaceblaster.component

import com.badlogic.ashley.core.Component
import com.badlogic.ashley.core.ComponentMapper

object Mappers {

    val BOUNDS = mapper<BoundsComponent>()
    val POSITION = mapper<PositionComponent>()
    val MOVEMENT = mapper<MovementComponent>()
}

inline fun <reified T : Component> mapper(): ComponentMapper<T> = ComponentMapper.getFor(T::class.java)
