package korshukou;

import korshukou.dao.AccountRepository;
import korshukou.entity.Account;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SubscriberApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubscriberApplication.class, args);
    }

    @Bean
    CommandLineRunner init(final AccountRepository accountRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... arg0) throws Exception {
                accountRepository.save(new Account("Eggzz", "password"));
            }
        };
    }
}
