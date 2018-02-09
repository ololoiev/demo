package korshukou.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import korshukou.entity.Customer;
import korshukou.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/customers")
@Api(value = "/api/v1/customers", description = "Manage customers")
public class CustomerController {
    private CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @ApiOperation(value = "Return customer by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomer(
            @ApiParam(value = "Id of customer to lookup for", required = true)
            @PathVariable String id) {
        Customer customer = service.find(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Update customer",
            notes = "Required customer instance")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Customer> updateCustomer(
            @ApiParam(value = "Customer instance", required = true)
            @RequestBody Customer customer) {
        customer = service.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Create customer",
            notes = "Required customer instance")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Customer> newCustomer(
            @ApiParam(value = "Customer instance", required = true)
            @RequestBody Customer customer) {
        customer = service.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "List all customers",
            notes = "List all customers using paging",
            response = Customer.class,
            responseContainer = "List"
    )
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAllCustomer(
            @RequestParam int page,
            @RequestParam int size) {
        List<Customer> customers = service.findAll(new PageRequest(page, size));
        return new ResponseEntity(customers, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete customer by id")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity deleteCustomer(
            @ApiParam(value = "Customer id", required = true)
            @PathVariable String id) {
        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Return 200 if customer exist")
    @RequestMapping(value = "/{id}/exist", method = RequestMethod.GET)
    public ResponseEntity isCustomerExist(
            @ApiParam(value = "Id of customer to lookup for", required = true)
            @PathVariable String id) {
        Customer customer = service.find(id);
        if ((customer != null) && id.equals(customer.getId())) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }
}
