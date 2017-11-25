package com.stehno.spaceblaster.component

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Circle

class BoundsComponent(var bounds: Circle = Circle()) : Component