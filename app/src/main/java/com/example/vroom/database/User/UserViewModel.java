package com.example.vroom.database.User;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<List<User>> getAllUser;

    public UserViewModel(@NonNull Application application) {

        super(application);
        userRepository=new UserRepository(application);
        getAllUser= userRepository.getAllUser();
    }
    public void insert (User user){
        userRepository.insert(user);
    }
    public void update (User user){
        userRepository.update(user);
    }
    public void delete (User user){
        userRepository.delete(user);
    }
    public void deleteAll (User user){
        userRepository.deleteAllUser();
    }
    public LiveData<List<User>> getGetAllUser() {
        return getAllUser;
    }
}
