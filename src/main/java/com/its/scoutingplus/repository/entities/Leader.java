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
@Table(name="leaders")
public class Leader {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_group_id", nullable = false)
    @ToString.Exclude
    private Group leaderGroup;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Leader leader = (Leader) o;
        return getId() != null && Objects.equals(getId(), leader.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

