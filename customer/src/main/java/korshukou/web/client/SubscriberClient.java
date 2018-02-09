package korshukou.web.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SubscriberClient {
    @Value("${serviceUrl.subscriber}")
    protected String serviceUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public SubscriberClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void deleteAllByCustomerId(String id) {
        String url = serviceUrl +
                "/api/v1/subscribers?customerId=" +
                id;
        restTemplate.delete(url);
    }
}
