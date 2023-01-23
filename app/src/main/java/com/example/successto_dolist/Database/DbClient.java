package com.example.successto_dolist.Database;

import android.content.Context;

import androidx.room.Room;

public class DbClient {

    private Context context;
    private static DbClient client;
    private DbTodoDao dbTodoDao;

    private DbClient(Context context){
        this.context = context;
        dbTodoDao = Room.databaseBuilder(
                context,
                DbTodoDao.class,
                "SuccessToDoList.db"
        ).build();
    }
    public static synchronized DbClient getInstance(Context context){
        if (client == null){
            client = new DbClient(context);
        }
        return client;
    }
    public  DbTodoDao getDbTodoDao(){
        return dbTodoDao;
    }
}
