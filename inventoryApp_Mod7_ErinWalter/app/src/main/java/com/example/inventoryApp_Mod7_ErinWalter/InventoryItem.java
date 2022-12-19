package com.example.inventoryApp_Mod7_ErinWalter;

public class InventoryItem {
    long Id;
    String Name;
    String Description;
    int Amount;


    public InventoryItem(long id, String name, String description, int amount)
    {
        Id = id;
        Name = name;
        Description = description;
        Amount = amount;
    }

}
