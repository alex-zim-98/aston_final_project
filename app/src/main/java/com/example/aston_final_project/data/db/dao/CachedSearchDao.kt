package com.example.aston_final_project.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aston_final_project.data.db.entity.CASHED_SEARCH_TABLE
import com.example.aston_final_project.data.db.entity.CashTable
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

@Dao
interface CachedSearchDao {
    @Query("DELETE FROM $CASHED_SEARCH_TABLE")
    fun clearNewsEverything(): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewsEverything(news: List<CashTable>): Completable

    @Query("SELECT * FROM $CASHED_SEARCH_TABLE WHERE articleNews_sourceNews_id = :id")
    fun getNewsEverythingById(id: String): Maybe<CashTable>

    @Query("SELECT * FROM $CASHED_SEARCH_TABLE")
    fun getNewsEverything(): Single<List<CashTable>>
}
