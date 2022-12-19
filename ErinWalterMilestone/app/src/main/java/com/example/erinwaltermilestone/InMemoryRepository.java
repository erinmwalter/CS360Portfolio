package com.example.erinwaltermilestone;

//just a dummy in memory repository class that
//will be used just for testing purposes of the UI
public class InMemoryRepository {
    public InventoryItem[] SampleInventory;

    //constructor will build a sample inventory to use
    public InMemoryRepository() {
        InventoryItem SampleItemOne = new InventoryItem(1, "Sample Item", 10);
        InventoryItem SampleItemTwo = new InventoryItem(2, "Sample Item 2", 5);
        InventoryItem SampleItemThree = new InventoryItem(3, "Sample Item 3", 0);
        InventoryItem SampleItemFour = new InventoryItem(4, "Sample Item 4", 22);
        InventoryItem SampleItemFive = new InventoryItem(5, "Sample Item 5", 7);
    }
}
