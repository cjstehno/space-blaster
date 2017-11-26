package com.stehno.spaceblaster.util

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion

operator fun TextureAtlas.get(regionName: String): TextureRegion? = this.findRegion(regionName)