package com.example.vroom.database.User;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    //singleton class
    private static UserDatabase instance;
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    //to ensure the database doesnt have any duplicates
    static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class, "User_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .allowMainThreadQueries()
                    .build();

        }
        return instance;

    }

    public abstract UserDAO userDAO();

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDAO userDAO;

        private PopulateDbAsyncTask(UserDatabase db) {
            userDAO = db.userDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //table user (username, full name, email, current address, phone number, password)
            userDAO.insert(new User("U01", "Anwar Chong", "Mohamad Anwar", "anwarbinbujang@gmail.com",
                    "No.19 Jalan Surada 2", "+60113735411", "anwarb", "No Document Submitted", "No Document Submitted"));
//            userDAO.insert(new User("U01","Anwar Chong","Mohamad Anwar","anwarbinbujang@gmail.com",
//                    "No.19 Jalan Surada 2","+60113735411","anwarb"));
            return null;
        }
    }

}
