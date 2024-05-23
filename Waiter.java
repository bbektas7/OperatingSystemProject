package com.mycompany.operatingsystem;


public class Waiter extends Thread implements Runnable {
    private Tray borekTray;
    private Tray cakeTray;
    private Tray drinkTray;
    private static int guest = 8;
    public Waiter(Tray borekTray, Tray cakeTray, Tray drinkTray) {
        this.borekTray = borekTray;
        this.cakeTray = cakeTray;
        this.drinkTray = drinkTray;
    }

    public synchronized void notifyWaitress(String name) {
        System.out.println(name + " finished his meal");
        guest--;
        if (guest==0) {
            System.out.println("All guests finished their meal, the party is over!");
            System.exit(0);
        }

    }


    @Override
    public void run() {
        while (true) {
            synchronized (borekTray) {
                if (borekTray.getQuantity() <= 1) {
                    try {
                        borekTray.fill();
                        System.out.println("Waiter: Borek tray filled.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            synchronized (cakeTray) {
                if (cakeTray.getQuantity() <= 1) {
                    try {
                        cakeTray.fill();
                        System.out.println("Waiter: Cake tray filled.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            synchronized (drinkTray) {
                if (drinkTray.getQuantity() <= 1) {
                    try {
                        drinkTray.fill();
                        System.out.println("Waiter: Drink tray filled.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


        }
    }
}

