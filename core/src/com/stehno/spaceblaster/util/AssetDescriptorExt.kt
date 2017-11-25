package com.stehno.spaceblaster.util

import com.badlogic.gdx.assets.AssetDescriptor

inline fun <reified T : Any> assetDescriptor(path: String): AssetDescriptor<T> = AssetDescriptor<T>(path, T::class.java)