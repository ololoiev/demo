package korshukou.util;

import korshukou.entiry.Customer;
import korshukou.entiry.ExtendCustomer;

public class Utils {
    public static Customer mapToCustomer(ExtendCustomer extendCustomer) {
        Customer customer = new Customer();

        customer.setId(extendCustomer.getId());
        customer.setName(extendCustomer.getName());
        customer.setDescription(extendCustomer.getDescription());
        customer.setAddress(extendCustomer.getAddress());
        customer.setEmail(extendCustomer.getEmail());

        return customer;
    }

    public static ExtendCustomer mapToExtendedCustomer(Customer customer) {
        ExtendCustomer extendCustomer = new ExtendCustomer();

        extendCustomer.setId(customer.getId());
        extendCustomer.setName(customer.getName());
        extendCustomer.setDescription(customer.getDescription());
        extendCustomer.setAddress(customer.getAddress());
        extendCustomer.setEmail(customer.getEmail());

        return extendCustomer;
    }
}
