package com.example.aston_final_project.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aston_final_project.data.db.entity.CASHED_SEARCH_TABLE
import com.example.aston_final_project.data.db.entity.CashTable

@Dao
interface CachedSearchDao {
    @Query("DELETE FROM $CASHED_SEARCH_TABLE")
    suspend fun clearNewsEverything()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsEverything(news: List<CashTable>)

    @Query("SELECT * FROM $CASHED_SEARCH_TABLE WHERE articleNews_sourceNews_id = :id")
    suspend fun getNewsEverythingById(id: String): CashTable

//    @Query("SELECT * FROM $CASHED_SEARCH_TABLE")
//    suspend fun getNewsEverything(): List<Article>
}
