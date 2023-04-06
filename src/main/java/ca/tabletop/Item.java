/*
 *  Author :                Tyler Osborne
 *  Project:                Tabletop Master System
 *  File:                   Item.java
 *  Description:
 *          This is the item class. The item class is used discribe an item such as a tankard and manage that in an inventory
 */
package ca.tabletop;

public class Item 
{
    public Item(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    private int weight;
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    private int value;
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    
    public String toString()
    {
        String string = name + " | " + weight + "lbs | " + value + "G | ";
        return string;
    }
}
