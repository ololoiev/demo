package korshukou.web.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.DELETE;

@Component
public class SubscriberClient {
    @Value("${serviceUrl.subscriber}")
    protected String serviceUrl;

    private final RestTemplate restTemplate;
    private HttpHeaders headers = null;

    @Autowired
    public SubscriberClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void deleteAllByCustomerId(String id) {
        String url = serviceUrl + "/api/v1/subscribers?customerId=" + id;
        if (headers == null) {
            login("Eggzz", "password");
        }
        restTemplate.exchange(url, DELETE, new HttpEntity<String>(headers), String.class);
    }

    private void login(String userName, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<String> request = new HttpEntity<>("password=password&username=Eggzz", headers);
        ResponseEntity<String> response = restTemplate.exchange(serviceUrl + "/login", HttpMethod.POST, request, String.class);
        createCookie(response.getHeaders());
    }

    private void createCookie(HttpHeaders givenHeaders) {
        String set_cookie = givenHeaders.getFirst(HttpHeaders.SET_COOKIE);
        String JSESSIONID = set_cookie.substring(11).split(";")[0];
        headers = new HttpHeaders();
        headers.add("Cookie", "JSESSIONID=" + JSESSIONID);
    }
}
