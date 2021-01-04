package io.github.jesterz91.finedust.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepo(location: Location)

    @Query("SELECT * FROM location ORDER BY id ASC")
    fun getLocations(): List<Location>

    @Query("DELETE FROM location")
    fun clearAll()

//    @Query("SELECT * FROM location")
//    fun getCityLocations(): LiveData<List<Location>>
//
//    @Query("SELECT * FROM location")
//    fun fetch(): Flowable<List<Repo>>

}