package korshukou.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Subscriber {

    @Id
    private String id;
    private String name;
    private String surname;
    private String email;
    private String customerId;
    private String displayName;
}
