package com.stehno.spaceblaster

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.stehno.spaceblaster.asset.AssetDescriptors
import com.stehno.spaceblaster.asset.RegionNames
import com.stehno.spaceblaster.component.*
import com.stehno.spaceblaster.config.GameConfig
import com.stehno.spaceblaster.util.get

class EntityFactory(private val engine: PooledEngine,
                    private val assetManager: AssetManager) {

    private val gameAtlas = assetManager[AssetDescriptors.GAME_ATLAS]

    fun addPlayer() {
        val startX = GameConfig.WORLD_CENTER_X
        val startY = 0.2f

        engine.addEntity(engine.createEntity()
            .add(bounds(startX, startY, GameConfig.PLAYER_SIZE / 2f))
            .add(position(startX, startY))
            .add(movement())
            .add(engine.createComponent(PlayerComponent::class.java))
            .add(engine.createComponent(WorldWrapComponent::class.java))
            .add(texture(gameAtlas[RegionNames.PLAYER]!!))
            .add(dimension(GameConfig.PLAYER_SIZE, GameConfig.PLAYER_SIZE)))
    }

    fun addAsteroid(startX: Float, startY: Float) {
        engine.addEntity(engine.createEntity()
            .add(bounds(startX, startY, GameConfig.ASTEROID_BOUNDS_RADIUS))
            .add(position(startX, startY))
            .add(movement(-GameManager.INSTANCE.difficultyLevel.speed))
            .add(engine.createComponent(CleanupComponent::class.java))
            .add(engine.createComponent(AsteroidComponent::class.java))
            .add(texture(gameAtlas[RegionNames.ASTEROID]!!))
            .add(dimension(GameConfig.ASTEROID_SIZE, GameConfig.ASTEROID_SIZE)))
    }

    fun addBackground() {
        engine.addEntity(engine.createEntity()
            .add(texture(gameAtlas[RegionNames.STARFIELD]!!))
            .add(position(0f, 0f))
            .add(dimension(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT)))
    }

    private fun dimension(w: Float, h: Float) = engine.createComponent(DimensionComponent::class.java).apply {
        width = w
        height = h
    }

    private fun bounds(startX: Float, startY: Float, radius: Float) = engine.createComponent(BoundsComponent::class.java).apply {
        bounds.set(startX, startY, radius)
    }

    private fun position(startX: Float, startY: Float) = engine.createComponent(PositionComponent::class.java).apply {
        x = startX
        y = startY
    }

    private fun movement(speed: Float = 0f) = engine.createComponent(MovementComponent::class.java).apply {
        ySpeed = speed
    }

    private fun texture(textureRegion: TextureRegion) = engine.createComponent(TextureComponent::class.java).apply {
        region = textureRegion
    }
}