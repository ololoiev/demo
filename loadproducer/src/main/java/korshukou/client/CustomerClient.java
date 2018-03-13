package korshukou.client;

import korshukou.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.logging.Logger;

import static korshukou.util.Utils.prepareCustomers;

@Component
public class CustomerClient {
    protected String serviceUrl = "http://localhost:8080";

    private final RestTemplate restTemplate;
    private static final Logger LOGGER = Logger.getLogger(CustomerClient.class.getName());

    @Autowired
    public CustomerClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public long test(int amount) {
        String url = serviceUrl + "/api/v1/customers";
        String deleteUrl = serviceUrl + "/api/v1/customers/";

        ArrayList<Customer> customers = prepareCustomers(amount);

        long startTime = System.nanoTime();
        for (int i = 0; i<amount; i++) {
            restTemplate.postForEntity(url, customers.get(i), Customer.class).getBody();
            restTemplate.delete(deleteUrl + i);
        }
        long endTime = System.nanoTime();
        LOGGER.info(amount + "\t\ttake:\t\t" + (endTime - startTime) / 1000000000.0);
        return (endTime - startTime);
    }
}
