package com.its.scoutingplus.repository.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter @Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String birthDate;

    @NonNull
    private String address;

    @NonNull
    private String city;

    @NonNull
    private String postCode;

    @NonNull
    private String phoneNumber;

    private String phoneNumber_optional;

    @NonNull
    private String email;

    private String email_optional;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Person person = (Person) o;
        return id != null && Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
