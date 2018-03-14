package korshukou.client;

import korshukou.entiry.Customer;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CustomerClient {
    @Value("${serviceUrl.customer}")
    protected String serviceUrl;

    private WebClient client = WebClient
            .builder()
            .baseUrl(serviceUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    public Flux<Customer> getAll() {
        return client.get()
                .exchange()
                .block()
                .bodyToFlux(Customer.class);
    }

    public Mono<Customer> create(Customer customer) {
        return client.post()
                .body(BodyInserters.fromObject(customer))
                .exchange()
                .block()
                .bodyToMono(Customer.class);
    }

    public Mono<Customer> getById(String id) {
        return client.get()
                .uri(id)
                .exchange()
                .block()
                .bodyToMono(Customer.class);
    }

    public Mono<Void> deleteById(String id) {
        return client.delete()
                .uri(id)
                .exchange()
                .then();
    }

    public Flux<Customer> update(Flux<Customer> customerFlux) {
        return client.put()
                .body(BodyInserters.fromObject(customerFlux))
                .exchange()
                .block()
                .bodyToFlux(Customer.class);
    }
}
