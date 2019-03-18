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
    	this.size = size;
        q = new LinkedList<Customer>();
    }

    /**
     * This method should put the customer into the waitingArea
     *
     * @param customer A customer created by Door, trying to enter the waiting area
     */
    public synchronized void enter(Customer customer) {
    	while(q.size() >= this.size) {
    		try {
    			this.wait();
    		}
    		catch(InterruptedException e){
    			e.printStackTrace();
    		}
    	}
		if(SushiBar.isOpen) {
			this.notifyAll();
			q.add(customer);
			SushiBar.write(Thread.currentThread().getName() + ": Kunde #" + customer.getCustomerID() + " venter");
		}
    }

    /**
     * @return The customer that is first in line.
     */
    public synchronized Customer next() {
    	System.out.println("NEXT");
    	while(SushiBar.isOpen && q.isEmpty()) {
    		try {
    			this.wait();
    			System.out.println("hei");
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    	}
    	if(!q.isEmpty()) {
    		System.out.println("hei");
    		this.notifyAll();
    		return q.remove();
    	}
    	return null;
    }
    
    
    public boolean isEmpty() {
        return q.isEmpty();
    }

    // Add more methods as you see fit
    
}
