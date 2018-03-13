package korshukou.service.implementation;

import korshukou.dao.SubscriberRepository;
import korshukou.entity.Subscriber;
import korshukou.service.SubscriberService;
import korshukou.web.client.CustomerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class SubscriberServiceImpl implements SubscriberService{

    private static final Logger LOGGER = Logger.getLogger(SubscriberServiceImpl.class.getName());

    private SubscriberRepository repository;
    private CustomerClient client;

    @Autowired
    public SubscriberServiceImpl(SubscriberRepository repository, CustomerClient client) {
        this.repository = repository;
        this.client = client;
    }

    @Override
    public Subscriber save(Subscriber subscriber) {
        LOGGER.info("Save subscriber: " + subscriber);
        if (client.isExistCustomer(subscriber.getCustomerId())) {
            subscriber = repository.save(subscriber);
            LOGGER.info("Saved subscriber: " + subscriber);
            return subscriber;
        }else {
            LOGGER.info("Unsaved subscriber: " + subscriber);
            throw new IllegalArgumentException("Customer with this id doesn't exist.");
        }

    }

    @Override
    public Subscriber find(String id) {
        LOGGER.info("Searching subscriber with id: " + id);
        return repository.findById(id).get();
    }

    @Override
    public List<Subscriber> findAll(Pageable pageable) {
        List<Subscriber> subscribers = repository.findAll(pageable).getContent();
        LOGGER.info("Found subscribers: " + subscribers.size());
        return subscribers;
    }

    @Override
    public void delete(String id) {
        LOGGER.info("Delete subscriber with id: " + id);
        repository.deleteById(id);
        LOGGER.info("Deleted subscriber with id: " + id);
    }

    @Override
    public void deleteAll(String customerId) {
        LOGGER.info("Delete subscribers with customer id: " + customerId);
        repository.deleteAllByCustomerId(customerId);
        LOGGER.info("Deleted subscribers with customer id: " + customerId);
    }
}
