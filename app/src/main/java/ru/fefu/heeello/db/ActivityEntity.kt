package ru.fefu.heeello.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import ru.fefu.heeello.model.ActivityTypeEnum

@Entity(tableName = "activities")
data class ActivityEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val activityType: ActivityTypeEnum,
    val startTime: Long,
    val endTime: Long,
    val coordinates: List<Pair<Double, Double>>,
    val distance: Float
)

class CoordinatesConverter {
    @TypeConverter
    fun fromCoordinates(coordinates: List<Pair<Double, Double>>): String {
        return coordinates.joinToString(";") { "${it.first},${it.second}" }
    }

    @TypeConverter
    fun toCoordinates(data: String): List<Pair<Double, Double>> {
        if (data.isEmpty()) return emptyList()
        return data.split(";").map {
            val parts = it.split(",")
            Pair(parts[0].toDouble(), parts[1].toDouble())
        }
    }
} 