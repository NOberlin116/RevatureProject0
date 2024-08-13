package com.revature.DAOs;

import com.revature.Models.Movie;
import com.revature.Models.User;

import java.util.ArrayList;

public interface UserDAOInterface {

    ArrayList<User> getUsers();

    User getUserByID(int id);

    User insertUser(User user);

    User updateUserEmail(int id, String newEmail);

}
