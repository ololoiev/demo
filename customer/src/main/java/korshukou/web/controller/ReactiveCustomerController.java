package korshukou.web.controller;

import io.swagger.annotations.Api;
import korshukou.dao.ReactiveCustomerRepository;
import korshukou.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v2/customers")
@Api(value = "/api/v2/customers", description = "Manage customers fast")
public class ReactiveCustomerController {

    private ReactiveCustomerRepository repository;

    @Autowired
    public ReactiveCustomerController(ReactiveCustomerRepository repository) {
        this.repository = repository;
    }


    @PostMapping
    Mono<Customer> create(@RequestBody Customer personStream) {
        return repository.save(personStream);
    }

    @DeleteMapping(value = "/{id}")
    Mono<Void> delete(@RequestParam String id) {
        return repository.deleteById(id);
    }

    @GetMapping
    Flux<Customer> getAll() {
        return repository.findAll();
    }
}
