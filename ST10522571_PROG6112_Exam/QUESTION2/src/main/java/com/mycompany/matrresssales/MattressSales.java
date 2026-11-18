/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.matrresssales;

/**
 *
 * @author nmahl
 */


import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MattressSales extends JFrame {

    private JTextField txtName = new JTextField(20);
    private JComboBox<String> cmbProduct = new JComboBox<>(new String[]{
        "Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"});
    private JTextField txtPrice = new JTextField(20);
    private final JButton btnProcess = new JButton("PROCESS");

    public MattressSales() {
        setTitle("Product App");
        setSize(450, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 5, 5));

        // Q.2.2 - form controls
        add(new JLabel("Customer Name:"));
        add(txtName);
        add(new JLabel("Select Product:"));
        add(cmbProduct);
        add(new JLabel("Enter Price:"));
        add(txtPrice);
        add(new JLabel(""));
        add(btnProcess);

        // Q.2.4 - menu system
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem processItem = new JMenuItem("Process");
        JMenuItem clearItem = new JMenuItem("Clear");

        fileMenu.add(exitItem);
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        // Events
        btnProcess.addActionListener(e -> processSale());
        processItem.addActionListener(e -> processSale());   // same logic as button
        exitItem.addActionListener(e -> System.exit(0));
        clearItem.addActionListener(e -> {
            txtName.setText("");
            txtPrice.setText("");
            cmbProduct.setSelectedIndex(0);
        });
    }

    // Q.2.3 - process logic
    private void processSale() {
        String name = txtName.getText().trim();
        String product = cmbProduct.getSelectedItem().toString();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the customer name.");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(txtPrice.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid price.");
            return;
        }

        ProductCalculations calc = new ProductCalculations(price);
        double vat = calc.GetVat();
        double commission = calc.GetCommission();
        double total = calc.GetTotal();

        String message = "Customer Name: " + name + "\n"
                + "Product: " + product + "\n"
                + "VAT: R " + vat + "\n"
                + "COMMISSION: R " + commission + "\n"
                + "TOTAL: R " + total;

        JOptionPane.showMessageDialog(this, message);

        // Save to data.txt (true = append, so old records are kept)
        try (PrintWriter out = new PrintWriter(new FileWriter("data.txt", true))) {
            out.println("DATA LOG");
            out.println("**********************************");
            out.println("Customer Name: " + name);
            out.println("Product: " + product);
            out.println("VAT: R " + vat);
            out.println("COMMISSION: R " + commission);
            out.println("TOTAL: R " + total);
            out.println("**********************************");
            out.println();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not save to file.");
        }
    }

    public static void main(String[] args) {
        new MattressSales().setVisible(true);
    }
}
