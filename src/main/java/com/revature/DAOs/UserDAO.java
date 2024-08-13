package com.revature.DAOs;

import com.revature.Models.Movie;
import com.revature.Models.User;
import com.revature.Utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO implements UserDAOInterface {
    @Override
    public ArrayList<User> getUsers() {

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "select * from users order by user_id asc";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            ArrayList<User> users = new ArrayList<>();

            while (rs.next()) {
                User u = new User(
                        rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email")
                );

                users.add(u);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User getUserByID(int id) {

        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "select * from users where user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email")
                );
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not find movie by ID.");
        }

        return null;
    }


    @Override
    public User insertUser(User user) {

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "insert into users (first_name, last_name, email) values (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getFirst_name());
            ps.setString(2, user.getLast_name());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to insert new User.");
        }

        return null;
    }

    @Override
    public User updateUserEmail(int id, String newEmail) {

        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "update users set email = ? where user_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newEmail);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
