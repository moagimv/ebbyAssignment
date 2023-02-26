/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.lutendobakery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author S2026987
 */
public class LutendoBakery {

    public static void main(String[] args) {

        System.err.println("================ Begin ======================");
        System.err.println("==========Welcome to Lutendo's Bakery==========");
        System.err.println("\n\\n\\n");

        Scanner in = new Scanner(System.in);

        SalesProducts salesProducts = new SalesProducts();
        ExpDate expDate = new ExpDate(salesProducts.getSize());
        PriorityProducts priorityProducts = new PriorityProducts();
        ExpStock expStock = new ExpStock();
        SoldProducts soldProducts = new SoldProducts();

        System.out.println("Please enter the product name");
        String name = in.nextLine();

        int batchNumber;
        String batchdetails;
        ExpiryDate expiryDate;
        Item item;

        //adding of items
        for (int x = 0; x <= salesProducts.getSize(); x++) {
            System.out.println("Please enter the batch number");
            batchNumber = in.nextInt();

            expiryDate = new ExpiryDate();
            batchdetails = name + " batch " + batchNumber;
            expiryDate.setBatch(batchdetails);

            boolean correctDate = false;
            do {
                try {
                    System.out.println("Please enter the expiry date in the format YYYY-MM-DD (eg: 2023-02-25)");
                    String inputExpiryDate = in.nextLine();
                    SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
                    Date theExpiryDate = sdfSource.parse(inputExpiryDate);
                    expiryDate.setDate(theExpiryDate);
                    correctDate = true;
                } catch (ParseException ex) {
                    System.out.println("You have entered and incorrect date formate.");
                }
            } while(correctDate = false);
            
            expDate.push(expiryDate);

            item = new Item();
            item.setName(name);
            item.setBatch(batchdetails);
            item.setExpiryDate(expiryDate);
            salesProducts.enQueue(item);
        } //selling of stock
        int sellNext = 0;
        int counter = 0;
        while (salesProducts.isFull()) {
            System.out.println("Do you wanna sell your " + name + ". Enter 1 for Yes and 0 for No");
            sellNext = in.nextInt();
            if (sellNext == 1) {
                counter++;
                Item soldItem = salesProducts.deQueue();
                if (soldItem.getExpiryDate().getDate().after(new Date())) {
                    expStock.addItem(soldItem);
                } else {
                    soldProducts.addItem(soldItem);
                }
                expDate.removeExpiryDate(soldItem.getExpiryDate());
            } else {
                break;
            }
        }
        System.out.println(counter + name + " sold successfully");

        System.out.println("\n\\n\\n\\n");
        //update the expiry dates of each product
        System.out.println("Updating og expired products begins");
        while (expDate.isFull()) {
            for (int x = 0; x <= salesProducts.getItems().length - 1; x++) {
                Item foundItem = salesProducts.getItems()[x];
                ExpiryDate itemExpiryDate = foundItem.getExpiryDate();
                if (expDate.findExpiryDate(itemExpiryDate) != null) {
                    expDate.removeExpiryDate(itemExpiryDate);
                    itemExpiryDate.setDate(new Date());
                    expDate.push(itemExpiryDate);

                    Item oldItem = foundItem;
                    foundItem.setExpiryDate(itemExpiryDate);
                    salesProducts.updateQueue(oldItem, foundItem);
                }
            }
        }
        System.out.println("Updating og expired products End");

        System.out.println("\n\\n\\n\\n");
        System.out.println("Fetching priority products begins");
        //priority products
        for (int x = 0; x <= salesProducts.getSize(); x++) {
            for (Item theItem : salesProducts.getLatestProducts()) {
                priorityProducts.enQueue(theItem);
            }
        }
        System.out.println("Fetching priority products ends");

        System.out.println("\n\\n\\n\n");
        //display priority products
        priorityProducts.display();

        System.out.println("\n\\n\\n\n");
        System.out.println("Selling 4 Priority Products Begins");
        //sell 4 batched
        for (int x = 0; x <= 4; x++) {
            Item soldItem = priorityProducts.deQueue();
            if (soldItem.getExpiryDate().getDate().after(new Date())) {
                expStock.addItem(soldItem);
            } else {
                soldProducts.addItem(soldItem);
            }
            expDate.removeExpiryDate(soldItem.getExpiryDate());

            System.out.println(soldItem.getBatch() + " has been sold");
        }
        System.out.println("Selling 4 Priority Products end");

        System.out.println("\n\\n\\n\\n");
        //display elements of the priority products
        priorityProducts.display();

        System.out.println("\n\\n\\n\\n");
        System.out.println("Restore remaining products begins");
        //restore remaining elements to sales products
        salesProducts.restoreProducts();
        salesProducts.setItems(priorityProducts.getItems());
        System.out.println("Restore remaining products end");

        System.out.println("\n\\n\\n\\n Expiry Dates");
        //display elements of expDate, expStock and soldProducts
        expDate.printStack();
        System.out.println("\n\\n\\n\\n Expired Stock");
        expStock.displayExpireStock();
        System.out.println("\n\\n\\n\\n Sold Products");
        soldProducts.displaySoldProducts();

        System.out.println("\n\\n\\n\\n");
        //print number of elements in expDate, expStock, soldProducts, priorityProducts, and salesProducts
        int totExpiryDates = expDate.getExpiryDates().length - 1;
        System.err.println("Total Expiery Dates: " + totExpiryDates);
        System.err.println("Total Expiery Stock: " + expStock.getItems().size());
        System.err.println("Total Sold Products: " + soldProducts.getItems().size());
        int totPriorityProducts = priorityProducts.getItems().length - 1;
        System.err.println("Total Priority Products: " + totPriorityProducts);
        int totSalesProducts = salesProducts.getItems().length - 1;
        System.err.println("Total Sales Products: " + totSalesProducts);

        System.err.println("\n\\n\\n");
        System.err.println("=================== The End =====================");
    }
}
