package com.docmation;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bradl
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemsFile implements Serializable
{
    File myFile;
    public ItemsFile(String path)
    {
        super();
        File dir = new File(path);
        File tempFile = new File(path + "/items.txt");
        if(!tempFile.exists())
        {
            dir.mkdir();
            this.myFile = dir;
        }
        else this.myFile = tempFile;
    }
    
    public void storeItems(Item item)
    {
        try {
            FileOutputStream fos = new FileOutputStream(myFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(item);
            oos.close();
        } catch (Exception e) {
            System.out.print("Exception: " + e.getMessage());
        }
    }
    
    public void storeItems(Item[] items)
    {
        try {
            FileOutputStream fos = new FileOutputStream(myFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Item item : items) {
                oos.writeObject(item);
            }
            oos.close();
        } catch (Exception e) {
            System.out.print("Exception: " + e.getMessage());
        }
    }
    
    
    public Item[] retrieveItems()
    {
        Item[] items = new Item[3];
        try
        {
            FileInputStream fis= new FileInputStream(myFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            for(int i = 0; i < 3; i++)
            {
                items[i] = (Item)ois.readObject();
            }
            ois.close();
        } catch (Exception e) {
            System.out.print("Exception: " + e.getMessage());
        }
        return items;
    }
    
    public void printItems(PrintWriter writer)
    {
        try
        {
            FileInputStream fis= new FileInputStream(myFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            for(int i = 0; i < 1; i++)
            {
                Item item = (Item)ois.readObject();
                writer.println("itemName: " + item.itemName);
                writer.println("item_ID: " + item.item_ID);
                writer.println("price: " + item.price);
                writer.println("image_URL: " + item.image_URL);
            }
            ois.close();
        } catch (Exception e) {
            System.out.print("Exception: " + e.getMessage());
        }
    }
}
