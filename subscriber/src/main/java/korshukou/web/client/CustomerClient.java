package korshukou.web.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomerClient {
    @Value("${serviceUrl.customer}")
    protected String serviceUrl;

    private RestTemplate restTemplate;

    @Autowired
    public CustomerClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isExistCustomer(String id) {
        String url = serviceUrl +
                "/api/v1/customers/" +
                id +
                "/exist";
        ResponseEntity<String> out= restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        return out.getStatusCode() == HttpStatus.OK;
    }
}
