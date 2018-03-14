package korshukou.entiry;

import lombok.Data;

import java.util.Set;

@Data
public class ExtendCustomer {
    private String id;
    private String name;
    private String description;
    private String email;
    private String address;
    private Set<Subscriber> subscribers;
}
