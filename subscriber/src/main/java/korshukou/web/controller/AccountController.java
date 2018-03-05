package korshukou.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import korshukou.dao.AccountRepository;
import korshukou.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/accounts")
@Api(value = "/api/v1/accounts", description = "Manage account")
public class AccountController {
    private AccountRepository repository;

    @Autowired
    public AccountController(AccountRepository repository) {
        this.repository = repository;
    }

    @ApiOperation(
            value = "Create account",
            notes = "Required account instance")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Account> newSubscriber(
            @ApiParam(value = "Account instance", required = true)
            @RequestBody Account account) {

        return new ResponseEntity<>(repository.save(account), HttpStatus.CREATED);
    }
}
