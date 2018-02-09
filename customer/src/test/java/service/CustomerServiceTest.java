package service;

import korshukou.dao.CustomerRepository;
import korshukou.entity.Customer;
import korshukou.service.implementation.CustomerServiceImpl;
import korshukou.web.client.SubscriberClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @Mock
    private SubscriberClient client;

    @InjectMocks
    private CustomerServiceImpl service;

    private Customer customer;
    private final String ID = "5a7d503668c4141b702235d7";

    @Before
    public void before(){
        customer = new Customer();
        customer.setId(ID);
    }

    @Test
    public void save(){
        service.save(customer);
        verify(repository).save(customer);
    }

    @Test
    public void find(){
        service.find(ID);
        verify(repository).findOne(ID);
    }

    @Test(expected = NullPointerException.class)
    public void findAll(){
        service.findAll(any(PageRequest.class));
        verify(repository).findAll(any(Pageable.class));
    }

    @Test
    public void delete(){
        service.delete(ID);
        verify(client).deleteAllByCustomerId(ID);
        verify(repository).delete(ID);
    }

}
