package com.google.authentication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "USERS")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @Column(unique = true)
    private String id;
    private String givenName;
    private String familyName;
    private String email;
    private String information;

    @SuppressWarnings("unused")
    public String getFullName() {
        return String.format("%s %s", givenName, familyName);
    }
}
