package korshukou.controller;

import korshukou.client.CustomerClient;
import korshukou.entiry.Customer;
import korshukou.entiry.ExtendCustomer;
import korshukou.service.ClientService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class MainController {
    private ClientService service;

    @Autowired
    public MainController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    Mono<ExtendCustomer> create(@RequestBody ExtendCustomer extendCustomer) {
        return service.create(extendCustomer);
    }

    @DeleteMapping(value = "/{id}")
    Mono<Void> delete(@RequestParam String id) {
        return service.delete(id);
    }

    @GetMapping
    Flux<ExtendCustomer> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    Mono<ExtendCustomer> get(@RequestParam String id){
        return service.get(id);
    }

    @PutMapping
    Flux<ExtendCustomer> update(@RequestBody Publisher<ExtendCustomer> extendCustomerFlux){
        return service.update(extendCustomerFlux);
    }
}
