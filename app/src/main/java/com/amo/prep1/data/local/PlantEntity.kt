package com.amo.prep1.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amo.prep1.model.Plant

@Entity(tableName = "plants")
data class PlantEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name= "name") val name : String = "",
    @ColumnInfo(name = "description") val description : String = ""
)


/**
 * Map Entities to Domain models
 */
fun List<PlantEntity>.asDomainModel(): List<Plant>{
    return map {
        Plant(plantId = it.id , name = it.name , description = it.description)
    }
}