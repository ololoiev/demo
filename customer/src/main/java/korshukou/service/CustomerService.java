package korshukou.service;

import korshukou.entity.Customer;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    Customer save(Customer customer);

    Customer find(String id);

    List<Customer> findAll(Pageable pageable);

    void delete(String id);
}
