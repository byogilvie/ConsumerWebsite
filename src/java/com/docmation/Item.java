package com.docmation;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
/**
 *
 * @author bradl
 */
public class Item implements Serializable
{
    public String itemName;
    public String item_ID;
    public String price;
    public String image_URL;
    public Item(String itemName, String item_ID, String price, String image_URL)
    {
        super();
        this.itemName = itemName;
        this.item_ID = item_ID;
        this.price = price;
        this.image_URL = image_URL;
    }
    public Item()
    {
        super();
    }
}
