package korshukou.benchmark;

import korshukou.entity.Customer;
import org.openjdk.jmh.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.logging.Logger;

import static korshukou.util.Utils.prepareCustomers;

@Component
public class CustomerClient {

    @State(Scope.Benchmark)
    public static class ExecutionPlan {

        @Param({ "10", "20", "1", "1", "1" })
        public int iterations;
        public RestTemplate restTemplate;
        public String serviceUrl = "http://localhost:8080";
        @Setup(Level.Invocation)
        public void setUp() {
            restTemplate = new RestTemplate();
        }
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 1)
    public void test(ExecutionPlan plan) {
        String url = plan.serviceUrl + "/api/v1/customers";
        String deleteUrl = plan.serviceUrl + "/api/v1/customers/";

        ArrayList<Customer> customers = prepareCustomers(plan.iterations);

        //long startTime = System.nanoTime();
        for (int i = 0; i<plan.iterations; i++) {
            plan.restTemplate.postForEntity(url, customers.get(i), Customer.class).getBody();
            plan.restTemplate.delete(deleteUrl + i);
        }
        //long endTime = System.nanoTime();
        //LOGGER.info(amount + "\t\ttake:\t\t" + (endTime - startTime) / 1000000000.0);
    }
}
