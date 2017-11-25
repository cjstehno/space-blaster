package com.stehno.spaceblaster.config

object GameConfig {

    // world
    const val WORLD_WIDTH = 6.0f
    const val WORLD_HEIGHT = 10.0f

    const val WORLD_CENTER_X = WORLD_WIDTH / 2f
    const val WORLD_CENTER_Y = WORLD_HEIGHT / 2f

    // desktop
    const val DESKTOP_WIDTH = 480
    const val DESKTOP_HEIGHT = 800

    // hud
    const val HUD_WIDTH = 480f
    const val HUD_HEIGHT = 800f
    const val SCORE_MAX_TIME = 1.25f

    // player
    const val PLAYER_SIZE = 0.8f
    const val PLAYER_MAX_X_SPEED = 0.25f
    const val LIVES_START = 3

    // asteroid
    const val ASTEROID_SPAWN_TIME = 0.35f
    const val ASTEROID_BOUNDS_RADIUS = 0.3f
    const val ASTEROID_SIZE = ASTEROID_BOUNDS_RADIUS * 2f
}