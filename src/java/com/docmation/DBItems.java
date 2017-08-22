package com.docmation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import com.sun.xml.internal.ws.encoding.XMLHTTPBindingCodec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author bradl
 */
public class DBItems {

    public DBItems() {
        super();

    }

    public void clearCart(String userName) {
        try {
            System.out.println("Connecting to database");

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
            PreparedStatement stmt = con.prepareStatement("delete from user_cart where username=?");
            stmt.setString(1, userName);
            stmt.execute();

            con.commit();

            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Exception insert:" + e.getMessage());
            StackTraceElement[] ste = e.getStackTrace();
            System.out.println("Exception: " + e.getMessage());
            for (int i = 0; i < ste.length; i++) {
                System.out.println(ste[i].toString());
            }
        }
    }

    public Item findItem(String id) {
        Item currItem = new Item();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
            PreparedStatement stmt = con.prepareStatement("select * from items where item_ID=?");
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                currItem.item_ID = rs.getString("item_ID");
                currItem.itemName = rs.getString("itemName");
                currItem.price = rs.getString("price");
                currItem.image_URL = "images/" + rs.getString("image_URL");
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
        return currItem;
    }

    public String removeItemsFromCart(String itemId, String username, int num) {
        int itemID = Integer.parseInt(itemId);
        try {
            System.out.println("Connecting to database");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
            PreparedStatement temp = con.prepareStatement("select * from user_cart where username=? AND item_ID=?");
            temp.setString(1, username);
            temp.setInt(2, itemID);
            ResultSet rs = temp.executeQuery();
            while (rs.next()) {
                int itemNum = rs.getInt("numItems");
                System.out.println(itemNum);
                if (itemNum <= num) {
                    PreparedStatement delete = con.prepareStatement("delete from user_cart where username=? AND item_ID=?");
                    delete.setString(1, username);
                    delete.setInt(2, itemID);
                    System.out.println(delete.toString());
                    delete.execute();
                    con.commit();
                    delete.close();
                } else {
                    PreparedStatement add = con.prepareStatement("UPDATE user_cart SET numItems=? WHERE username=? AND item_ID=?");
                    add.setInt(1, itemNum - num);
                    add.setString(2, username);
                    add.setInt(3, itemID);
                    add.execute();
                    con.commit();
                    add.close();
                }
            }
            rs.close();
            temp.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Exception insert:" + e.getMessage());
            StackTraceElement[] ste = e.getStackTrace();
            System.out.println("Exception: " + e.getMessage());
            for (int i = 0; i < ste.length; i++) {
                System.out.println(ste[i].toString());
            }
        }
        return "Cart updated.";
    }

    public String addItem(Item smItem) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
            PreparedStatement stmt = con.prepareStatement("insert into items values(?,?,?,?)");
            stmt.setInt(1, Integer.parseInt(smItem.item_ID));
            stmt.setString(2, smItem.itemName);
            stmt.setDouble(3, Double.parseDouble(smItem.price));
            stmt.setString(4, smItem.image_URL);

            stmt.execute();

            con.commit();

            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Exception insert:" + e.getMessage());
            StackTraceElement[] ste = e.getStackTrace();
            System.out.println("Exception: " + e.getMessage());
            for (int i = 0; i < ste.length; i++) {
                System.out.println(ste[i].toString());
            }
        }
        return "Item Created";
    }

    public int getNumItems(String username, String itemId) {
        int itemNum = 0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
            PreparedStatement temp = con.prepareStatement("select * from user_cart where username=? AND item_ID=?");
            temp.setString(1, username);
            temp.setInt(2, Integer.parseInt(itemId));
            ResultSet rs = temp.executeQuery();
            if (rs.next()) {
                itemNum = rs.getInt("numItems");
            }
            con.close();
        } catch (SQLException ex) {
            StackTraceElement[] ste = ex.getStackTrace();
            System.out.println("SQLException insert: " + ex.getMessage());
            for (int i = 0; i < ste.length; i++) {
                System.out.println(ste[i].toString());

            }
        } catch (Exception ex) {
            System.out.println("Exception insert:" + ex.getMessage());
            StackTraceElement[] ste = ex.getStackTrace();
            System.out.println("Exception: " + ex.getMessage());
            for (int i = 0; i < ste.length; i++) {
                System.out.println(ste[i].toString());

            }
        }
        return itemNum;
    }

    public String addItemToCart(String userName, String itemId) {
        try {
            System.out.println("Inserting item in user_cart table");

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
            PreparedStatement temp = con.prepareStatement("select * from user_cart where username=? AND item_ID=?");
            temp.setString(1, userName);
            temp.setInt(2, Integer.parseInt(itemId));
            ResultSet rs = temp.executeQuery();
            if (rs.next()) {
                int itemNum = rs.getInt("numItems");
                itemNum += 1;
                System.out.println(itemNum);
                PreparedStatement add = con.prepareStatement("Update user_cart set numItems=? where username=? AND item_ID=?");
                add.setInt(1, itemNum);
                add.setString(2, userName);
                add.setInt(3, Integer.parseInt(itemId));
                add.execute();
                con.commit();
                add.close();
            } else {
                PreparedStatement stmt = con.prepareStatement("insert into user_cart values(?,?, 1)");
                System.out.println(userName + " " + itemId);
                stmt.setString(1, userName);
                stmt.setInt(2, Integer.parseInt(itemId));
                stmt.execute();
                con.commit();
                stmt.close();
            }
            con.close();
        } catch (SQLException ex) {
            StackTraceElement[] ste = ex.getStackTrace();
            System.out.println("SQLException insert: " + ex.getMessage());
            for (int i = 0; i < ste.length; i++) {
                System.out.println(ste[i].toString());

            }
        } catch (Exception ex) {
            System.out.println("Exception insert:" + ex.getMessage());
            StackTraceElement[] ste = ex.getStackTrace();
            System.out.println("Exception: " + ex.getMessage());
            for (int i = 0; i < ste.length; i++) {
                System.out.println(ste[i].toString());

            }

        }
        return "Successfully created item";
    }

    public ArrayList getUserItems(String userName) {
        ArrayList<String> itemList = new ArrayList();

        try {
            System.out.println("getUserItems method");
            System.out.println("userName: " + userName);

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");

            PreparedStatement stmt = con.prepareStatement("select * from user_cart where username=?");
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String itemId = null;
                System.out.println("Fetching Item record");
                itemId = rs.getString("ITEM_ID");
                itemList.add(itemId);

            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            StackTraceElement[] ste = ex.getStackTrace();
            System.out.println("Exception: " + ex.getMessage());
            for (int i = 0; i < ste.length; i++) {
                System.out.println(ste[i].toString());

            }
        } catch (Exception ex) {
            System.out.println("Exception:" + ex.getMessage());
            StackTraceElement[] ste = ex.getStackTrace();
            System.out.println("Exception: " + ex.getMessage());
            for (int i = 0; i < ste.length; i++) {
                System.out.println(ste[i].toString());

            }

        }
        return itemList;
    }

    public ArrayList<Item> getItems() {
        ArrayList<Item> itemsList = new ArrayList<Item>();

        System.out.println("Connecting to database");
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();

        }

        System.out.println("Oracle JDBC Driver Registered!");
        //Class.forName("oracle.jdbc.driver.OracleDriver");
        try {

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
            PreparedStatement stmt = con.prepareStatement("select * from items");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Item currItem = new Item();
                System.out.println("Fetching Item record");
                currItem.item_ID = rs.getString("item_ID");
                currItem.itemName = rs.getString("itemName");
                currItem.price = rs.getString("price");
                currItem.image_URL = "images/" + rs.getString("image_URL");
                itemsList.add(currItem);
            }
            System.out.println(itemsList.size());
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
        return itemsList;
    }

}
