package com.revature.Services;

import com.revature.DAOs.UserDAO;
import com.revature.Models.User;

import java.util.ArrayList;

public class UserService {

    UserDAO uDAO = new UserDAO();

    public ArrayList<User> getUsers() { return uDAO.getUsers(); }

    public User insertUser(User user) throws IllegalArgumentException {

        if (user.getFirst_name() == null || user.getFirst_name().equals("")) {
            throw new IllegalArgumentException("First Name must not be empty.");
        }
        if (user.getLast_name() == null || user.getLast_name().equals("")) {
            throw new IllegalArgumentException("Last Name must not be empty.");
        }
        if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new IllegalArgumentException("First Name must not be empty.");
        }

        uDAO.insertUser(user);

        return user;
    }

    public void updateUserEmail(int userID, String newEmail) throws IllegalArgumentException {
        if (userID <= 0) {
            throw new IllegalArgumentException("User ID must be 1 or higher.");
        }
        if (newEmail == null || newEmail.equals("")) {
            throw new IllegalArgumentException("New email must not be empty.");
        }
        uDAO.updateUserEmail(userID, newEmail);
    }

}
