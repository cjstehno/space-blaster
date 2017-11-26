package com.stehno.spaceblaster

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.assets.AssetManager
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

        val bounds = engine.createComponent(BoundsComponent::class.java).apply {
            bounds.set(startX, startY, GameConfig.PLAYER_SIZE / 2f)
        }

        val position = engine.createComponent(PositionComponent::class.java).apply {
            x = startX
            y = startY
        }

        val player = engine.createComponent(PlayerComponent::class.java)

        val movement = engine.createComponent(MovementComponent::class.java)

        val worldWrap = engine.createComponent(WorldWrapComponent::class.java)

        val texture = engine.createComponent(TextureComponent::class.java).apply {
            region = gameAtlas[RegionNames.PLAYER]!!
        }

        val dimension = engine.createComponent(DimensionComponent::class.java).apply {
            width = GameConfig.PLAYER_SIZE
            height = GameConfig.PLAYER_SIZE
        }

        val entity = engine.createEntity()
            .add(bounds)
            .add(position)
            .add(movement)
            .add(player)
            .add(worldWrap)
            .add(texture)
            .add(dimension)

        engine.addEntity(entity)
    }

    fun addAsteroid(startX: Float, startY: Float) {
        val bounds = engine.createComponent(BoundsComponent::class.java).apply {
            bounds.set(startX, startY, GameConfig.ASTEROID_BOUNDS_RADIUS)
        }

        val movement = engine.createComponent(MovementComponent::class.java).apply {
            ySpeed = -GameManager.INSTANCE.difficultyLevel.speed
        }

        val position = engine.createComponent(PositionComponent::class.java).apply {
            x = startX
            y = startY
        }

        val cleanup = engine.createComponent(CleanupComponent::class.java)

        val asteroid = engine.createComponent(AsteroidComponent::class.java)

        val texture = engine.createComponent(TextureComponent::class.java).apply {
            region = gameAtlas[RegionNames.ASTEROID]!!
        }

        val dimension = engine.createComponent(DimensionComponent::class.java).apply {
            width = GameConfig.ASTEROID_SIZE
            height = GameConfig.ASTEROID_SIZE
        }

        val entity = engine.createEntity()
            .add(bounds)
            .add(movement)
            .add(position)
            .add(cleanup)
            .add(asteroid)
            .add(texture)
            .add(dimension)

        engine.addEntity(entity)
    }

    fun addBackground() {
        val texture = engine.createComponent(TextureComponent::class.java).apply {
            region = gameAtlas[RegionNames.STARFIELD]!!
        }

        val position = engine.createComponent(PositionComponent::class.java).apply {
            x = 0f
            y = 0f
        }

        val dimension = engine.createComponent(DimensionComponent::class.java).apply {
            width = GameConfig.WORLD_WIDTH
            height = GameConfig.WORLD_HEIGHT
        }

        val entity = engine.createEntity()
            .add(texture)
            .add(position)
            .add(dimension)

        engine.addEntity(entity)
    }
}