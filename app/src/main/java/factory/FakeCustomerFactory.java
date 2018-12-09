package factory;

import java.util.ArrayList;
import domain.Customer;

/**
 * Used for creating fake users. This will be replaced by proper Register, Login and Logout system.
 */
public class FakeCustomerFactory implements ICustomerFactory {
    private final ArrayList<Customer> customers;

    public FakeCustomerFactory() {
        this.customers = new ArrayList<Customer>(){{
            add(new Customer("sune@student.sdu.dk"));
            add(new Customer("anna@student.sdu.dk"));
            add(new Customer("veena@student.sdu.dk"));
            add(new Customer("admira@student.sdu.dk"));
       }};
    }

    /**
     * Used for getting all customers.
     *
     * @return List of all customers.
     */
    public ArrayList<Customer> getCustomers() {
        return customers;
    }
}
