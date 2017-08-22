/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docmation;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author bradl
 */
public class User {

    public String userName;
    public String firstName;
    public String lastName;
    public String email;
    public boolean isAdmin;
    public String currency;
    //public Date birthday;

    public User(String userName, String firstName, String lastName, String email, boolean isAdmin, String currency) {
        super();
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        //this.setBirthday();
        this.email = email;
        this.isAdmin = isAdmin;
        this.currency = currency;
    }

    public User(String userName) {
        super();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
            PreparedStatement stmt = con.prepareStatement("select * from user_data where username=?");
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                this.userName = userName;
                this.firstName = rs.getString("firstName");
                this.lastName = rs.getString("lastName");
                this.email = rs.getString("email");
                this.isAdmin = Boolean.parseBoolean(rs.getString("isAdmin"));
                //this.setBirthday(rs.getString("birthday"));
                this.currency = rs.getString("currency");
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            StackTraceElement[] stack = e.getStackTrace();
            System.out.println("Exception: " + e.getMessage());
            for (int i = 0; i < stack.length; i++) {
                System.out.println(stack[i].toString());
            }
        } catch (Exception e) {
            StackTraceElement[] stack = e.getStackTrace();
            System.out.println("Exception: " + e.getMessage());
            for (int i = 0; i < stack.length; i++) {
                System.out.println(stack[i].toString());
            }
        }
        this.userName = userName;
    }

    public void editUserDB() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
            PreparedStatement stmt = con.prepareStatement("UPDATE user_data "
                    + "SET firstName=?, lastName=?, email=?, currency=? WHERE username=?");
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, currency);
            stmt.setString(5, userName);

            stmt.execute();
            con.commit();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            StackTraceElement[] stack = e.getStackTrace();
            System.out.println("Exception: " + e.getMessage());
            for (int i = 0; i < stack.length; i++) {
                System.out.println(stack[i].toString());
            }
        } catch (Exception e) {
            StackTraceElement[] stack = e.getStackTrace();
            System.out.println("Exception: " + e.getMessage());
            for (int i = 0; i < stack.length; i++) {
                System.out.println(stack[i].toString());
            }
        }

    }

}
