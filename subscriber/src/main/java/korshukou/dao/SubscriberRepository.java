package korshukou.dao;

import korshukou.entity.Subscriber;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubscriberRepository extends MongoRepository<Subscriber, String > {
    void deleteAllByCustomerId(String customerId);
}
