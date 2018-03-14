package korshukou.service;


import korshukou.entiry.ExtendCustomer;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {
    Mono<ExtendCustomer> create(ExtendCustomer extendCustomer);

    Mono<Void> delete(String id);

    Flux<ExtendCustomer> getAll();

    Mono<ExtendCustomer> get(String id);

    Flux<ExtendCustomer> update(Flux<ExtendCustomer> extendCustomerFlux);
}
