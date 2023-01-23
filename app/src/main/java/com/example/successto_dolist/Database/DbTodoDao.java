package com.example.successto_dolist.Database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.successto_dolist.Model.ListModel;


@Database(
        entities = {ListModel.class},
        version = 1,
        exportSchema = false
)
public abstract class DbTodoDao extends RoomDatabase {
    public abstract TodoDao todoDao();

}
