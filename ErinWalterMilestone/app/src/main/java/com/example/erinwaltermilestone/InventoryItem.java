package com.example.erinwaltermilestone;

public class InventoryItem {
    //fields to match database column setup
    private int Id;
    private String ItemName;
    private int NumberInStock;

    //constructor for InventoryItem
    public InventoryItem(int id, String itemName, int numberInStock)
    {
        Id = id;
        ItemName = itemName;
        NumberInStock = numberInStock;
    }
}
