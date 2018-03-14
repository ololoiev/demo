package korshukou.service;

import korshukou.client.CustomerClient;
import korshukou.entiry.Customer;
import korshukou.entiry.ExtendCustomer;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static korshukou.util.Utils.mapToCustomer;
import static korshukou.util.Utils.mapToExtendedCustomer;

@Service
public class ClientServiceImpl implements ClientService {
    private CustomerClient client;

    @Autowired
    public ClientServiceImpl(CustomerClient client) {
        this.client = client;
    }

    @Override
    public Mono<ExtendCustomer> create(ExtendCustomer extendCustomer) {
        client.create(mapToCustomer(extendCustomer));
        return null;
    }

    @Override
    public Mono<Void> delete(String id) {
        return client.deleteById(id);
    }

    @Override
    public Flux<ExtendCustomer> getAll() {
        return client.getAll()
                .flatMap(entity -> Mono.just(mapToExtendedCustomer(entity)));
    }

    @Override
    public Mono<ExtendCustomer> get(String id) {
        return client.getById(id)
                .map(entity -> mapToExtendedCustomer(entity));
    }

    @Override
    public Flux<ExtendCustomer> update(Flux<ExtendCustomer> extendCustomerFlux) {
        return client.update(extendCustomerFlux.map(extendCustomer->mapToCustomer(extendCustomer)))
                .map(customer -> mapToExtendedCustomer(customer));
    }
}
