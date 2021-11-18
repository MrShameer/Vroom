package com.example.vroom.database.User;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    private UserDAO userDAO;
    private LiveData<List<User>> getAllUser;

    public UserRepository(Application application){

        UserDatabase database=UserDatabase.getInstance(application);
        userDAO= database.userDAO();
        getAllUser=userDAO.getAllUser();

    }

    public void insert(User user){
    new InsertUserAsyncTask(userDAO).execute(user);
    }
    private static class InsertUserAsyncTask extends AsyncTask<User,Void,Void>
    {
        private UserDAO userDAO;
        private  InsertUserAsyncTask(UserDAO userDAO){
            this.userDAO=userDAO;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDAO.insert(users[0]);
            return null;
        }
    }

    public  void update(User user){
        new UpdateUserAsyncTask(userDAO).execute(user);

    }
    private static class UpdateUserAsyncTask extends AsyncTask<User,Void,Void>
    {
        private UserDAO userDAO;
        private  UpdateUserAsyncTask(UserDAO userDAO){
            this.userDAO=userDAO;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDAO.update(users[0]);
            return null;
        }
    }


    public  void delete(User user){
        new DeleteUserAsyncTask(userDAO).execute(user);
    }
    private static class DeleteUserAsyncTask extends AsyncTask<User,Void,Void>
    {
        private UserDAO userDAO;
        private  DeleteUserAsyncTask(UserDAO userDAO){
            this.userDAO=userDAO;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDAO.delete(users[0]);
            return null;
        }
    }

    public  void deleteAllUser(){
        new DeleteAllUserAsyncTask(userDAO).execute();
    }
    private static class DeleteAllUserAsyncTask extends AsyncTask<User,Void,Void>
    {
        private UserDAO userDAO;
        private  DeleteAllUserAsyncTask(UserDAO userDAO){
            this.userDAO=userDAO;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDAO.deleteAllUser();
            return null;
        }
    }

    public LiveData<List<User>> getAllUser(){
        return getAllUser;
    }







}
