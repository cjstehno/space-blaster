package com.stehno.spaceblaster.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle

/**
 * Converts a `String` into an internal GDX `FileHandle`.
 */
fun String.toInternalFile(): FileHandle = Gdx.files.internal(this)