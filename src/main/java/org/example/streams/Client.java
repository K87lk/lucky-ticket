package org.example.streams;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Client {
    private UUID clientId;
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private List<Account> accounts;
}
