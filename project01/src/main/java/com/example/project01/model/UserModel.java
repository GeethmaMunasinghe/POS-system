package com.example.project01.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    public static boolean authenticate(String email, String password) {
        String query= "SELECT * FROM users WHERE email=? AND password= ?";
        try(Connection conn=DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement=conn.prepareStatement(query)){
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet rs=preparedStatement.executeQuery();

            if (rs.next()){
                return true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
