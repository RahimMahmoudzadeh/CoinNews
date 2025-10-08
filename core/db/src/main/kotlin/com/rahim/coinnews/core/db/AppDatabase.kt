package com.rahim.coinnews.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rahim.coinnews.core.db.favorite.dao.FavoriteDao
import com.rahim.coinnews.core.db.favorite.model.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
