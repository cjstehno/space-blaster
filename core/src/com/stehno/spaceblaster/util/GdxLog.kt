package com.stehno.spaceblaster.util

import com.badlogic.gdx.utils.Logger

inline fun <reified T : Any> logger(): Logger = Logger(T::class.java.simpleName, Logger.DEBUG)
