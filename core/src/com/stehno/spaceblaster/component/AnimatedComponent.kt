package com.stehno.spaceblaster.component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion

class AnimatedComponent : Component {

    lateinit var animation: Animation<TextureRegion>
}