package com.stehno.spaceblaster.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.stehno.spaceblaster.SpaceBlasterGame;

fun main(args: Array<String>) {
    LwjglApplication(SpaceBlasterGame(), LwjglApplicationConfiguration())
}
