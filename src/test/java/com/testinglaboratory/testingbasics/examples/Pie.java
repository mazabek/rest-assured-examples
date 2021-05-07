package com.testinglaboratory.testingbasics.examples;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;


@Getter
@Setter
public class Pie {
    private String flavour;
    private String filling;
    private Optional<String> topping;

    public Pie(String flavour, String filling) {
        this.flavour = flavour;
        this.filling = filling;
        this.topping = Optional.empty();
    }
}
