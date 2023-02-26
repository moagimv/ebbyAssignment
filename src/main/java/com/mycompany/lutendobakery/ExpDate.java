/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lutendobakery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author S2026987
 */
public class ExpDate {

    private ExpiryDate expiryDates[];
    private int top;
    private int capacity;

    // Creating a stack
    public ExpDate(int size) {
        expiryDates = new ExpiryDate[size];
        capacity = size;
        top = -1;
    }

    // Add elements into stack
    public void push(ExpiryDate expiryDate) {
        if (isFull()) {
            System.out.println("OverFlow\nProgram Terminated\n");
            System.exit(1);
        }

        System.out.println("Inserting " + expiryDate.getBatch() + " with expiry date of " + expiryDate.getDate());
        expiryDates[++top] = expiryDate;
    }

    // Remove element from stack
    public ExpiryDate pop() {
        if (isEmpty()) {
            System.out.println("STACK EMPTY");
            System.exit(1);
        }
        return expiryDates[top--];
    }

    // Utility function to return the size of the stack
    public int size() {
        return top + 1;
    }

    // Check if the stack is empty
    public Boolean isEmpty() {
        return top == -1;
    }

    // Check if the stack is full
    public Boolean isFull() {
        return top == capacity - 1;
    }

    public void printStack() {
        for (int i = 0; i <= top; i++) {
            System.out.println(expiryDates[i].getBatch() + " with expiry date of " + expiryDates[i].getDate());
        }
    }

    public ExpiryDate[] getExpiryDates() {
        return expiryDates;
    }

    public void setExpiryDates(ExpiryDate[] expiryDates) {
        this.expiryDates = expiryDates;
    }
    
    public ExpiryDate findExpiryDate(ExpiryDate expiryDate){
        List<ExpiryDate> expiryDateList = new ArrayList<>(Arrays.asList(expiryDates));
        if(expiryDateList.contains(expiryDate)){
            return expiryDate;
        }
        return null;
    }
    
    public void removeExpiryDate(ExpiryDate expiryDate){
        List<ExpiryDate> expiryDateList = new ArrayList<>(Arrays.asList(expiryDates));
        expiryDateList.remove(expiryDate);
        expiryDates = expiryDateList.toArray(expiryDates);
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
