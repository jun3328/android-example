package io.github.jesterz91.finedust.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
data class Location(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val lat: Double = 0.0,
    val lon: Double = 0.0
)