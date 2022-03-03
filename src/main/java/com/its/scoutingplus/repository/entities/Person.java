package com.its.scoutingplus.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Person {

    private int id;
    private String firstName;
    private String lastName;
}
