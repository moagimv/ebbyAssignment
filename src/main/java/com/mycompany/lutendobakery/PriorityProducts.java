/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lutendobakery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author S2026987
 */
public class PriorityProducts {

    private int size = 10;
    private Item items[] = new Item[size];
    private int front, rear;
    private ExpStock expStock = new ExpStock();
    private SoldProducts soldProducts = new SoldProducts();

    public void SalesProducts() {
        front = -1;
        rear = -1;
    }

    public boolean isFull() {
        if (front == 0 && rear == size - 1) {
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        if (front == -1) {
            return true;
        } else {
            return false;
        }
    }

    public void enQueue(Item item) {
        if (isFull()) {
            System.out.println("Queue is full");
        } else {
            if (front == -1) {
                front = 0;
            }
            rear++;
            items[rear] = item;
            System.out.println("Inserted " + item.getName() + " on " + item.getBatch());
        }
    }

    public Item deQueue() {
        Item item;

        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        } else {
            item = items[front];

            if (front >= rear) {
                front = -1;
                rear = -1;
            } /* Q has only one element, so we reset the queue after deleting it. */ else {
                front++;
            }

            System.out.println("Deleted -> " + item.getBatch());

            return (item);
        }
    }

    public void addItemToExpiredStock(Item item) {
        expStock.addItem(item);
    }

    public void addItemToSoldProducts(Item item) {
        soldProducts.addItem(item);
    }

    public void updateQueue(Item oldItem, Item newItem) {
        if (isFull()) {
            System.out.println("Queue is full");
        } else {
            Item foundItem = findItem(oldItem);
            if (foundItem != null) {
                removeItem(foundItem);

                if (front == -1) {
                    front = 0;
                }
                rear++;
                items[rear] = newItem;
                System.out.println("Updated " + newItem.getName() + " on " + newItem.getBatch());
            }

        }
    }

    public void display() {
        /* Function to display elements of Queue */
        int i;
        if (isEmpty()) {
            System.out.println("Empty Queue");
        } else {
            System.out.println("\nFront index-> " + front);
            System.out.println("Items -> ");
            for (i = front; i <= rear; i++) {
                System.out.print(items[i].getName() + "  ");
                System.out.print(items[i].getBatch() + "  ");
                System.out.print(items[i].getExpiryDate().getDate() + "  \n");
            }

            System.out.println("\nRear index-> " + rear);
        }
    }

    public Item findItem(Item item) {
        List<Item> itemsList = new ArrayList<Item>(Arrays.asList(items));
        if (itemsList.contains(item)) {
            return item;
        }
        return null;
    }

    public void removeItem(Item item) {
        List<Item> itemsList = new ArrayList<Item>(Arrays.asList(items));
        itemsList.remove(item);
        items = itemsList.toArray(items);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public int getFront() {
        return front;
    }

    public void setFront(int front) {
        this.front = front;
    }

    public int getRear() {
        return rear;
    }

    public void setRear(int rear) {
        this.rear = rear;
    }

    public ExpStock getExpStock() {
        return expStock;
    }

    public void setExpStock(ExpStock expStock) {
        this.expStock = expStock;
    }

    public SoldProducts getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(SoldProducts soldProducts) {
        this.soldProducts = soldProducts;
    }

}
