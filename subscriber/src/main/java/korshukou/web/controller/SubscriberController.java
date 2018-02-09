package korshukou.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import korshukou.entity.Subscriber;
import korshukou.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/subscribers")
@Api(value = "/api/v1/subscribers", description = "Manage subscribers")
public class SubscriberController {
    private SubscriberService service;

    @Autowired
    public SubscriberController(SubscriberService service) {
        this.service = service;
    }

    @ApiOperation(value = "Return subscriber by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Subscriber> getSubscriber(
            @ApiParam(value = "Id of subscriber to lookup for", required = true)
            @PathVariable String id) {

        return new ResponseEntity<>(service.find(id), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Update subscriber",
            notes = "Required subscriber instance")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Subscriber> updateSubscriber(
            @ApiParam(value = "Subscriber instance", required = true)
            @RequestBody Subscriber subscriber) {

        return new ResponseEntity<>(service.save(subscriber), HttpStatus.OK);
    }

    @ApiOperation(
            value = "Create subscriber",
            notes = "Required subscriber instance")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Subscriber> newSubscriber(
            @ApiParam(value = "Subscriber instance", required = true)
            @RequestBody Subscriber subscriber) {

        return new ResponseEntity<>(service.save(subscriber), HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "List all subscribers",
            notes = "List all subscribers using paging",
            response = Subscriber.class,
            responseContainer = "List"
    )
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Subscriber>> getAllSubscriber(
            @RequestParam int page,
            @RequestParam int size) {

        List<Subscriber> subscribers = service.findAll(new PageRequest(page, size));
        return new ResponseEntity(subscribers, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete subscriber by id")
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public ResponseEntity deleteSubscriber(
            @ApiParam(value = "Subscriber id", required = true)
            @PathVariable String id) {

        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Delete subscriber by customer id")
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteSubscribers(
            @ApiParam(value = "Customer id", required = true)
            @RequestParam String customerId) {

        service.deleteAll(customerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
