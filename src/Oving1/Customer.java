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
    }

    /**
     *
     * @return Should return the customerID
     */
    public int getCustomerID() {
        // TODO Implement required functionality
    }

    // Add more methods as you see fit
}
