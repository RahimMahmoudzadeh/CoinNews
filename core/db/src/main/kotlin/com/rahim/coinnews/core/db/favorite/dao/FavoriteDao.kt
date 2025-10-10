package com.rahim.coinnews.core.db.favorite.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.rahim.coinnews.core.db.favorite.model.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Upsert
    suspend fun insertMarket(marketEntity: FavoriteEntity)

    @Query("SELECT * FROM favorite_table")
    fun getFavoriteMarketList(): Flow<List<FavoriteEntity>>

    @Delete
    suspend fun delete(favoriteEntity: FavoriteEntity)
}