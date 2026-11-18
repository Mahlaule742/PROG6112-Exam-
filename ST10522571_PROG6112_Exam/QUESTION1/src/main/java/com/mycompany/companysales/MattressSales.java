/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.companysales;

/**
 *
 * @author nmahl
 */

public class MattressSales {

    public static void main(String[] args) {

        // One-dimensional array storing product names
        String[] products = {
            "Single Mattress",
            "Double Mattress",
            "Queen Mattress"
        };

        // Two-dimensional array storing monthly sales
        // Rows represent products
        // Columns represent the three months
        int[][] sales = {
            {120, 150, 130},
            {100, 140, 160},
            {180, 200, 220}
        };

        // Create an object of Printproducts
        Printproducts report = new Printproducts();

        // Display the sales report
        System.out.println("==================================");
        System.out.println("       MATTRESS SALES REPORT");
        System.out.println("==================================");

        System.out.println("Sales period: Three Months");
        System.out.println();

        // Printing total products sold
        report.printTotalSales(products, sales);

        // Printing the avegae sales made
        report.printAverageSales(products, sales);

        // Print most popular productsol
        report.printMostPopularProduct(products, sales);

        System.out.println("==================================");
        System.out.println("       END OF SALES REPORT");
        System.out.println("==================================");
    }
}

