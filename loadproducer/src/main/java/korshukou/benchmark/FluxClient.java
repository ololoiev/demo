package korshukou.benchmark;

import korshukou.entity.Customer;
import org.openjdk.jmh.annotations.*;
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
            .build();
    private static final Logger LOGGER = Logger.getLogger(FluxClient.class.getName());

    @State(Scope.Benchmark)
    public static class ExecutionPlan {
        public WebClient client ;
        @Param({ "10", "20", "1", "1", "1" })
        public int iterations;
        @Setup(Level.Invocation)
        public void setUp() {
            client = WebClient
                    .builder()
                    .baseUrl(serviceUrl)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultUriVariables(Collections.singletonMap("url", serviceUrl))
                    .build();
        }
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 1)
    public void test(ExecutionPlan plan) {
        ArrayList<Customer> customers = prepareCustomers(plan.iterations);

        //long startTime = System.nanoTime();
        for (int i = 0; i < plan.iterations; i++) {

            plan.client.post()
                    .body(BodyInserters.fromObject(customers.get(i)))
                    .exchange()
                    .block().bodyToMono(Customer.class).block();
            plan.client.delete()
                    .uri(Integer.toString(i))
                    .exchange();
        }
        //long endTime = System.nanoTime();
        //LOGGER.info(amount + "\t\ttake:\t\t" + (endTime - startTime) / 1000000000.0);
    }
}
