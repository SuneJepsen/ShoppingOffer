package factory;

import java.util.ArrayList;

import domain.Customer;

/**
 * Interface for customer factory.
 */
public interface ICustomerFactory {
    /**
     * Gets all customers.
     *
     * @return Returns list of customers.
     */
    ArrayList<Customer> getCustomers();
}
