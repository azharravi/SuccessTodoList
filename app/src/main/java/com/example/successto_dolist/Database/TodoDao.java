package com.example.successto_dolist.Database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.successto_dolist.Model.ListModel;

import java.util.List;

@Dao
public interface TodoDao {

    @Query("Select * From ListModel Where completed = 0")
    List<ListModel> allTodo();

    @Query("Select * From ListModel where completed = 1")
    List<ListModel> allCompletedTodo();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ListModel listModel);

    @Update
    void update(ListModel listModel);

    @Delete
    void delete(ListModel listModel);
}
