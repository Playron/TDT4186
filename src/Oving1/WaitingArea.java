package Oving1;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class implements a waiting area used as the bounded buffer, in the producer/consumer problem.
 */
public class WaitingArea {

    /**
     * Creates a new waiting area.
     *
     * @param size The maximum number of Customers that can be waiting.
     */
	
	private Queue<Customer> q;
	private int size;
	
    public WaitingArea(int size) {
        q = new LinkedList<Customer>();
        this.size = size;
    }

    /**
     * This method should put the customer into the waitingArea
     *
     * @param customer A customer created by Door, trying to enter the waiting area
     */
    public synchronized void enter(Customer customer) {
        // TODO Implement required functionality
    	while(q.size() >= size) {
    		try {
    			customer.wait();
    		}
    		catch(Exception e){
    			e.printStackTrace();
    			
    		}if(SushiBar.isOpen) {
    			customer.notify();
    			q.add(customer);
    			SushiBar.write(Thread.currentThread().getName() + ": Kunde #:" + );
    		}
    		
    	}
    	
    	
    }

    /**
     * @return The customer that is first in line.
     */
    public synchronized Customer next() {
        // TODO Implement required functionality
    	
    }

    // Add more methods as you see fit
    
}
