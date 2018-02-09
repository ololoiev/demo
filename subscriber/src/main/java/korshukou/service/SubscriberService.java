package korshukou.service;

import korshukou.entity.Subscriber;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubscriberService {

    Subscriber save(Subscriber subscriber);

    Subscriber find(String id);

    List<Subscriber> findAll(Pageable pageable);

    void delete(String id);

    void deleteAll(String customerId);
}
