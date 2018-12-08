package factory;

import java.util.ArrayList;
import domain.Customer;

public class FakeUserFactory implements  IUserFactory {
    private final ArrayList<Customer> customers;

    public FakeUserFactory() {
        this.customers = new ArrayList<Customer>(){{
            add(new Customer("sune@student.sdu.dk"));
            add(new Customer("anna@student.sdu.dk"));
            add(new Customer("veena@student.sdu.dk"));
            add(new Customer("admira@student.sdu.dk"));
       }};
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }
}
