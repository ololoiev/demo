package korshukou.controller;

import korshukou.benchmark.CustomerClient;
import korshukou.benchmark.FluxClient;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    FluxClient fluxClient;
    CustomerClient client;

    @Autowired
    public TestController(FluxClient fluxClient, CustomerClient client) {
        this.fluxClient = fluxClient;
        this.client = client;
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/{amount}")
//    public void test(@PathVariable int amount) {
//        client.test(amount);
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/flux/{amount}")
//    public void testFlux(@PathVariable int amount) {
//        fluxClient.test(amount);
//    }
}
