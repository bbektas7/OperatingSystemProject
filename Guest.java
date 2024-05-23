package com.mycompany.operatingsystem;

import java.util.concurrent.*;

// Guest class
public class Guest extends Thread {
     private static final int MAX_BOREK = 4;   // Maximum number of böreks that can be eaten
    private static final int MAX_PASTA = 2;   // Maximum number of pastas that can be eaten
    private static final int MAX_DRINK = 4;   // Maximum number of drinks that can be consumed
    private static int totalBorek = 0;
    private static int totalPasta = 0;
    private static int totalDrink = 0;
    private int borekCount = 0;
    private int pastaCount = 0;
    private int drinkCount = 0;
    private Tray borekTray;
    private Tray pastaTray;
    private Tray drinkTray;
    private Waiter waiter;

    public Guest(String name, Tray borekTray, Tray pastaTray, Tray drinkTray, Waiter waiter) {
        super(name);
        this.borekTray = borekTray;
        this.pastaTray = pastaTray;
        this.drinkTray = drinkTray;
        this.waiter = waiter;
    }

    public void eatBorek(int amount) {
        borekCount += amount;
        totalBorek += amount;
    }

    public void eatPasta(int amount) {
        pastaCount += amount;
        totalPasta += amount;
    }

    public void drink(int amount) {
        drinkCount += amount;
        totalDrink += amount;
    }

    public static synchronized int getTotalBorek() {
        return totalBorek;
    }

    public static synchronized int getTotalCake() {
        return totalPasta;
    }

    public static synchronized int getTotalDrink() {
        return totalDrink;
    }

    @Override
    public void run() {
        while (true) {
            if (totalBorek >= 30 && totalPasta >= 15 && totalDrink >= 30) {
//                System.out.println(getName() + ": All food and drink quantities have been consumed. Exiting the program.");
                waiter.notifyWaitress(getName());
//                System.exit(0); // Terminate the program
            }

            try {
                synchronized (borekTray) {
                    if (totalBorek < 30 && borekCount < MAX_BOREK) {
                        borekTray.take(); // Take a borek from the tray
                        eatBorek(1); // Increment the borek eating count
                        System.out.println(getName() + ": Ate a borek. (Total: " + borekCount + " boreks)");
                    }
                }

                synchronized (pastaTray) {
                    if (totalPasta < 15 && pastaCount < MAX_PASTA) {
                        pastaTray.take(); // Take a pasta from the tray
                        eatPasta(1); // Increment the pasta eating count
                        System.out.println(getName() + ": Ate a cake. (Total: " + pastaCount + " cake)");
                    }
                }

                synchronized (drinkTray) {
                    if (totalDrink < 30 && drinkCount < MAX_DRINK) {
                        drinkTray.take(); // Take a drink from the tray
                        drink(1); // Increment the drink drinking count
                        System.out.println(getName() + ": Drank a drink. (Total: " + drinkCount + " drinks)");
                    }
                }

                // After eating and drinking, the guest can rest for a while
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
