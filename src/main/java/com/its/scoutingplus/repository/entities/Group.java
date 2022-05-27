package com.its.scoutingplus.repository.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter @Setter
@AllArgsConstructor
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Person> members;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Leader> leaders;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Group group = (Group) o;
        return id != null && Objects.equals(id, group.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}


