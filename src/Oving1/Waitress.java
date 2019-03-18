package Oving1;

import Oving1.Customer;
import Oving1.SushiBar;
import Oving1.WaitingArea;

/**
 * This class implements the consumer part of the producer/consumer problem.
 * One waitress instance corresponds to one consumer.
 */
public class Waitress implements Runnable {

	
	   private WaitingArea wa;
	   private Customer customer;
	
    /**
     * Creates a new waitress. Make sure to save the parameter in the class
     *
     * @param waitingArea The waiting area for customers
     */
    Waitress(WaitingArea waitingArea) {
    	
    	waitingArea = wa;
        // TODO Implement required functionality
    }

    /**
     * This is the code that will run when a new thread is
     * created for this instance
     */
    @Override
    public void run() {
    	while(SushiBar.isOpen ) { 
    		customer = wa.next();
    		if(customer != null) {
    			SushiBar.write(Thread.currentThread().getName() + ": Kunde #" + customer.getCustomerID() + " er servert");
    		}
    		try {
    			Thread.sleep(SushiBar.waitressWait);
    		}
    		catch(Exception e) {
    			e.printStackTrace();
    		}
    		
    		
    		SushiBar.write(Thread.currentThread().getName() + ": Kunde #" + customer.getCustomerID() +  " spiser");
    		customer.order();
    		try {
    			Thread.sleep(customer.getOrders() * SushiBar.customerWait);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    		SushiBar.write(Thread.currentThread().getName() + ": Kunde #" + customer.getCustomerID() + " drar");
        
    }
  }
    	

}



