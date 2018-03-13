package korshukou.util;

import korshukou.entity.Customer;

import java.util.ArrayList;

public final class Utils {
    private static final int AMOUNT = 1000;

    public static ArrayList<Customer> prepareCustomers(int amount) {
        ArrayList<Customer> customers = new ArrayList<>(amount <= 0 ? AMOUNT : amount);

        for(int i = 0; i < (amount <= 0 ? AMOUNT : amount); i++){
            Customer customer = new Customer();
            customer.setName("test");
            customer.setAddress("test");
            customer.setDescription("test");
            customer.setId(""+i);
            customers.add(customer);
        }
        return customers;
    }
}
