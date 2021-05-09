package com.testinglaboratory.testingbasics.examples;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyData {
    private String firstName;
    private String lastName;

    public MyData(String firstName, String lastName){
        this.lastName = lastName;
        this.firstName = firstName;
    }
}
