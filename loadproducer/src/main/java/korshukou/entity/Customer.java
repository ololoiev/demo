package korshukou.entity;

import lombok.Data;

@Data
public class Customer {
    private String id;
    private String name;
    private String description;
    private String email;
    private String address;
}
