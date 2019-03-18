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
    	wa = waitingArea;
    }

    /**
     * This is the code that will run when a new thread is
     * created for this instance
     */
    @Override
    public void run() {
    	while(SushiBar.isOpen || !wa.isEmpty()) { 
    		customer = wa.next();
	    	if(customer != null) {
	    		SushiBar.write(Thread.currentThread().getName() + ": Kunde #" + customer.getCustomerID() + " er servert");
	    		try {
	    			Thread.sleep(SushiBar.waitressWait);
	    		}
	    		catch(InterruptedException e) {
	    		
	    		}
	    		SushiBar.write(Thread.currentThread().getName() + ": Kunde #" + customer.getCustomerID() +  " spiser");
	    		customer.order();
	    		try {
	    			Thread.sleep(customer.getOrders() * SushiBar.customerWait);
	    		}catch(InterruptedException e) {
	    			
	    		}
	    		SushiBar.write(Thread.currentThread().getName() + ": Kunde #" + customer.getCustomerID() + " drar");
	    	}
    	}
    }
}



