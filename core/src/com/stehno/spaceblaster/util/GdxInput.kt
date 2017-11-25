package com.stehno.spaceblaster.util


import com.badlogic.gdx.Gdx

/**
 * Determines whether or not the key represented by the keycode (Int) has been pressed.
 */
fun Int.isKeyPressed() = Gdx.input.isKeyPressed(this)