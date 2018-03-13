package korshukou.controller;

import korshukou.client.CustomerClient;
import korshukou.client.FluxClient;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(method = RequestMethod.GET, value = "/{amount}")
    public void test(@PathVariable int amount) {
        client.test(amount);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/flux/{amount}")
    public void testFlux(@PathVariable int amount) {
        fluxClient.test(amount);
    }
}
