package com.mycompany.operatingsystem;

    // Tray class
public class Tray {
     private int quantity;

    public Tray(int quantity) {
        this.quantity = quantity;
    }

    public synchronized void fill() throws InterruptedException {
        while (quantity > 0) {
            wait(); // Wait if the tray is full
        }
        quantity = 5; // Fill the tray
        notifyAll(); // Notify waiting threads
    }

    public synchronized void take() throws InterruptedException {
        while (quantity == 0) {
            wait(); // Wait if the tray is empty
        }
        quantity--; // Take from the tray
        notifyAll(); // Notify waiting threads
    }

    public int getQuantity() {
        return quantity;
    }
}

