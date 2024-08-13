package com.revature.Controllers;

import com.revature.Models.User;
import com.revature.Services.UserService;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class UserController {

    UserService us = new UserService();

    public Handler getUsersHandler = ctx -> {
        ArrayList<User> users = us.getUsers();
        ctx.json(users);
        ctx.status(200);
    };

    public Handler insertUserHandler = ctx -> {
        User newUser = ctx.bodyAsClass(User.class);
        try {
            User insertedUser = us.insertUser(newUser);
            ctx.status(201);
            ctx.json(insertedUser);
        } catch (IllegalArgumentException e) {
            ctx.status(400);
            ctx.result(e.getMessage());
        } catch (NullPointerException e) {
            ctx.status(400);
            ctx.result("NullPointer got thrown");
        }
    };

    public Handler updateUserEmailHandler = ctx -> {
      User updateUser = ctx.bodyAsClass(User.class);

      try {
          us.updateUserEmail(updateUser.getUser_id(), updateUser.getEmail());
          ctx.status(204);
          ctx.json(updateUser);
      } catch (IllegalArgumentException e) {
          ctx.status(400).result(e.getMessage());
      } catch (Exception e) {
          ctx.status(500).result("Server error " + e.getMessage());
      }
    };

}
