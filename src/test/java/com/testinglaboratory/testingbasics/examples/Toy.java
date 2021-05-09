package com.testinglaboratory.testingbasics.examples;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Toy {
    private String make;
    private String name;
    private String colour;
    private String material;

    public Toy(String make, String name, String colour, String material){
        this.make = make;
        this.name=name;
        this.colour = colour;
        this.material=material;
    }
    public String greeting(){
       return "Name of your toy is: "+name + " colour: "+colour+" and material: "+material;
    }
}
