package com.stehno.spaceblaster.desktop

import com.badlogic.gdx.tools.texturepacker.TexturePacker
import com.badlogic.gdx.tools.texturepacker.TexturePacker.process

object AssetPacker {
    const val RAW_ASSETS_PATH = "desktop/assets-raw"
    const val ASSETS_PATH = "core/assets"
}

fun main(args: Array<String>) {
    val settings = TexturePacker.Settings()

    process(settings, "${AssetPacker.RAW_ASSETS_PATH}/game", AssetPacker.ASSETS_PATH, "game")
    process(settings, "${AssetPacker.RAW_ASSETS_PATH}/skin", AssetPacker.ASSETS_PATH, "skin")
}
