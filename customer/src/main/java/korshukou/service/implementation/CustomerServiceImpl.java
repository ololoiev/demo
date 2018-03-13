package korshukou.service.implementation;

import korshukou.dao.CustomerRepository;
import korshukou.entity.Customer;
import korshukou.service.CustomerService;
import korshukou.web.client.SubscriberClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = Logger.getLogger(CustomerServiceImpl.class.getName());

    private CustomerRepository repository;
    private SubscriberClient client;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository, SubscriberClient client) {
        this.repository = repository;
        this.client = client;
    }

    @Override
    public Customer save(Customer customer) {
        LOGGER.info("Save customer: " + customer);
        customer = repository.save(customer);
        LOGGER.info("Saved customer: " + customer);
        return customer;
    }

    @Override
    public Customer find(String id) {
        LOGGER.info("Searching customer with id: " + id);
        return repository.findById(id).get();
    }

    @Override
    public List<Customer> findAll(Pageable pageable) {
        List<Customer> customers = repository.findAll(pageable).getContent();
        LOGGER.info("Found customers: " + customers.size());
        return customers;
    }

    @Override
    public void delete(String id) {
        LOGGER.info("Delete customer with id: " + id);
        //client.deleteAllByCustomerId(id);
        repository.deleteById(id);
        LOGGER.info("Deleted customers with id: " + id);
    }

    @Override
    public HttpStatus exist(String id) {
        Customer customer = find(id);
        if ((customer != null) && id.equals(customer.getId())) {
            return HttpStatus.OK;
        } else {
            return HttpStatus.NO_CONTENT;
        }
    }
}
