package service;

import korshukou.dao.SubscriberRepository;
import korshukou.entity.Subscriber;
import korshukou.service.implementation.SubscriberServiceImpl;
import korshukou.web.client.CustomerClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SubscriberServiceTest {

    @Mock
    private SubscriberRepository repository;

    @Mock
    private CustomerClient client;

    @InjectMocks
    private SubscriberServiceImpl service;

    private Subscriber subscriber;
    private final String ID = "5a7d503668c4141b702235d7";
    private final String CUSTOMER_ID = "5a7d503668c4141b702235d3";

    @Before
    public void before(){
        subscriber = new Subscriber();
        subscriber.setId(ID);
        subscriber.setCustomerId(CUSTOMER_ID);
    }

    @Test
    public void save(){
        when(client.isExistCustomer(CUSTOMER_ID)).thenReturn(true);
        service.save(subscriber);
        verify(repository).save(subscriber);
        verify(client).isExistCustomer(CUSTOMER_ID);
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
        verify(repository).delete(ID);
    }

    @Test
    public void deleteAll(){
        service.deleteAll(ID);
        verify(repository).deleteAllByCustomerId(ID);
    }

}
