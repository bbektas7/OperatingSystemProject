package com.mycompany.operatingsystem;


public class OperatingSystem {
    public static void main(String[] args) {
       // Creating Tray objects
         Tray borekTray = new Tray(5);
        Tray cakeTray = new Tray(5);
        Tray drinkTray = new Tray(5);

        // Creating Waiter object
        Waiter waiter = new Waiter(borekTray, cakeTray, drinkTray);

        // Creating and starting Guest objects
        Guest[] guests = new Guest[8];
        for (int i = 0; i < 8; i++) {
            guests[i] = new Guest("Guest-" + (i + 1), borekTray, cakeTray, drinkTray, waiter);
            guests[i].start(); // Starting Guest threads
        }

        // Starting Waiter thread
        waiter.start();
        if (Guest.getTotalBorek() >= 30 && Guest.getTotalCake() >= 15 && Guest.getTotalDrink() >= 30) {
            waiter.interrupt();
        }
    }
}
