package com.stehno.spaceblaster.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.stehno.spaceblaster.SpaceBlasterGame;
import com.stehno.spaceblaster.config.GameConfig

fun main(args: Array<String>) {
    LwjglApplication(SpaceBlasterGame(), LwjglApplicationConfiguration().apply {
        width = GameConfig.DESKTOP_WIDTH
        height = GameConfig.DESKTOP_HEIGHT
    })
}
