package Oving1;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Oving1.Clock;
import Oving1.Door;
import Oving1.SushiBar;
import Oving1.WaitingArea;
import Oving1.Waitress;


public class SushiBar {

    //SushiBar settings
    private static int waitingAreaCapacity = 15;
    private static int waitressCount = 8;
    private static int duration = 4;
    public static int maxOrder = 10;
    public static int waitressWait = 50; // Used to calculate the time the waitress spends before taking the order
    public static int customerWait = 2000; // Used to calculate the time the customer spends eating
    public static int doorWait = 100; // Used to calculate the interval at which the door tries to create a customer
    public static boolean isOpen = true;

    //Creating log file
    private static File log;
    private static String path = "./";

    //Variables related to statistics
    public static SynchronizedInteger customerCounter;
    public static SynchronizedInteger servedOrders;
    public static SynchronizedInteger takeawayOrders;
    public static SynchronizedInteger totalOrders;


    public static void main(String[] args) {
        log = new File(path + "log.txt");

        //Initializing shared variables for counting number of orders
        customerCounter = new SynchronizedInteger(0);
        totalOrders = new SynchronizedInteger(0);
        servedOrders = new SynchronizedInteger(0);
        takeawayOrders = new SynchronizedInteger(0);
        
        WaitingArea waitingArea = new WaitingArea(waitingAreaCapacity);
        Clock klokke = new Clock(duration);
        Thread d�r = new Thread(new Door(waitingArea));
        d�r.start();

        ArrayList<Thread> waitresses = new ArrayList<>();
        for (int i = 0; i < waitressCount; i++) {
            Thread wThread = new Thread(new Waitress(waitingArea));
            waitresses.add(wThread);
            wThread.start();
        }

        try {
            d�r.join();
            for (int i = 0; i < waitressCount; i++) {
                waitresses.get(i).join();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        SushiBar.write("INGEN FLERE KUNDER I SJAPPA - SJAPPA ER N� STENGT!.\n");

        SushiBar.write("Ordre: " + SushiBar.totalOrders.get());
        SushiBar.write("Takeaway Ordre: " + SushiBar.takeawayOrders.get());
        SushiBar.write("Bar Ordre: " + SushiBar.servedOrders.get());
        
        


        // TODO initialize the bar and start the different threads
    }

    //Writes actions in the log file and console
    public static void write(String str) {
        try {
            FileWriter fw = new FileWriter(log.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Clock.getTime() + ", " + str + "\n");
            bw.close();
            System.out.println(Clock.getTime() + ", " + str);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
