package korshukou.dao;

import korshukou.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AccountRepository extends MongoRepository<Account, String> {
    Account findByUsername(String username);
}
