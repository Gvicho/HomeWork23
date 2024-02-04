package com.example.homework21.data.local.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object DataBaseConfig {

    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE ShmotkebisEntity ADD COLUMN category TEXT NOT NULL DEFAULT ''")
        }
    }

}