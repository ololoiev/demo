package korshukou.client;

import korshukou.entity.Customer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

import static korshukou.util.Utils.prepareCustomers;

@Component
public class FluxClient {
    protected static String serviceUrl = "http://localhost:8080/api/v2/customers/";

    private static WebClient client = WebClient
            .builder()
            .baseUrl(serviceUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultUriVariables(Collections.singletonMap("url", serviceUrl))
            .build();
    private static final Logger LOGGER = Logger.getLogger(FluxClient.class.getName());


    public long test(int amount) {
        ArrayList<Customer> customers = prepareCustomers(amount);

        long startTime = System.nanoTime();
        for (int i = 0; i < amount; i++) {

            client.post()
                    .body(BodyInserters.fromObject(customers.get(i)))
                    .exchange()
                    .block().bodyToMono(Customer.class).block();
            client.delete()
                    .uri(Integer.toString(i))
                    .exchange();
        }
        long endTime = System.nanoTime();
        LOGGER.info(amount + "\t\ttake:\t\t" + (endTime - startTime) / 1000000000.0);
        return (endTime - startTime);
    }
}
