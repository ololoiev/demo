package korshukou.web.client;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import java.io.IOException;
import java.util.Collections;

@Component
public class SubscriberClient {
    @Value("${serviceUrl.subscriber}")
    protected String serviceUrl;

    private final RestTemplate restTemplate;
    private BasicCookieStore store = null;

    @Autowired
    public SubscriberClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void deleteAllByCustomerId(String id) {
        HttpClientBuilder client = HttpClientBuilder.create();
        String url = serviceUrl + "/api/v1/subscribers?customerId=" + id;
        if(store == null) {
            login("Eggzz", "password");
        }
        client.setDefaultCookieStore(store);
        HttpDelete request = new HttpDelete(url);
        try {
            CloseableHttpClient build = client.build();
            build.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login(String userName, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<String> request = new HttpEntity<>("password=password&username=Eggzz", headers);
        ResponseEntity<String> response = restTemplate.exchange(serviceUrl + "/login", HttpMethod.POST, request, String.class);
        createCookie(response.getHeaders());
    }

    private BasicCookieStore createCookie(HttpHeaders headers) {
        String set_cookie = headers.getFirst(headers.SET_COOKIE);
        String JSESSIONID = set_cookie.substring(11).split(";")[0];
        BasicCookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", JSESSIONID);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookieStore.addCookie(cookie);
        store = cookieStore;
        return cookieStore;
    }
}
