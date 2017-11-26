package com.stehno.spaceblaster.asset

import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.stehno.spaceblaster.util.assetDescriptor

object AssetDescriptors {

    val FONT = assetDescriptor<BitmapFont>(AssetPaths.FONT)
    val ASTEROID_HIT_SOUND = assetDescriptor<Sound>(AssetPaths.ASTEROID_HIT_SOUND)
    val GAME_ATLAS = assetDescriptor<TextureAtlas>(AssetPaths.GAME_ATLAS)
}