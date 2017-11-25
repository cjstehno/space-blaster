package com.stehno.spaceblaster.asset

import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.stehno.spaceblaster.util.assetDescriptor

object AssetDescriptors {

    val FONT = assetDescriptor<BitmapFont>(AssetPaths.FONT)
    val ASTEROID_HIT_SOUND = assetDescriptor<Sound>(AssetPaths.ASTEROID_HIT_SOUND)
}