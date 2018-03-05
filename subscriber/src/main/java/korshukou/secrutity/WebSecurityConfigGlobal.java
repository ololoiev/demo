package korshukou.secrutity;


import korshukou.dao.AccountRepository;
import korshukou.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
class WebSecurityConfigGlobal extends GlobalAuthenticationConfigurerAdapter {

    private AccountRepository accountRepository;

    @Autowired
    public WebSecurityConfigGlobal(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            Account account = accountRepository.findByUsername(username);
            if (account != null) {
                return new User(account.getUsername(), account.getPassword(), true, true, true, true,
                        AuthorityUtils.createAuthorityList("USER"));
            } else {
                throw new UsernameNotFoundException("could not find the user '"
                        + username + "'");
            }
        };
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
