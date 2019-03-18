package Oving1;

import java.util.concurrent.ThreadLocalRandom;

import Oving1.SushiBar;

/**
 * This class implements a customer, which is used for holding data and update the statistics
 *
 */
public class Customer {

    /**
     *  Creates a new Customer.
     *  Each customer should be given a unique ID
     */
	
	private static int customer_numbers = 1;
	private int customer_id;
	public int orders;
	
    public Customer() {
        // TODO Implement required functionality
    	customer_id = SushiBar.customerCounter.get();
    	SushiBar.customerCounter.increment();
    }

    /**
     * Here you should implement the functionality for ordering food as described in the assignment.
     */
    public synchronized void order(){
        // TODO Implement required functionality
    	orders = ThreadLocalRandom.current().nextInt(0, SushiBar.maxOrder + 1);
    	int takeAway = ThreadLocalRandom.current().nextInt((orders == 0 ? 1 : 0), SushiBar.maxOrder - orders + 1);
    	SushiBar.servedOrders.add(orders);
    	SushiBar.takeawayOrders.add(takeAway);
    	SushiBar.totalOrders.add(orders + takeAway);
    	}

    /**
     *
     * @return Should return the customerID
     */
    public int getCustomerID() {
    	return customer_id;
    }
    
    public int getOrders() {
    	return orders;
    }
    
    

    // Add more methods as you see fit
}
