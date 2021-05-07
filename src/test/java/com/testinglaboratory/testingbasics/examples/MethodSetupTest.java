package com.testinglaboratory.testingbasics.examples;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodSetupTest {
    Pie pie;

    @BeforeEach
    public void bakeMaAPie(){
        pie = new Pie("Vanilla", "Love");
    }

    @Test
    public void shouldBeTraditional(){
        assertEquals(pie.getFlavour(), "Vanilla");
        assertEquals(pie.getFilling(), "Love");
        assertEquals(pie.getTopping(), Optional.empty());
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = {"sprinkles", "powder", "chocolate"})
    public void shouldBeToppedWithSomething(String topping){
        assertEquals(pie.getFlavour(), "Vanilla");
        assertEquals(pie.getFilling(), "Love");
        pie.setTopping(Optional.ofNullable(topping));
        assertEquals(pie.getTopping().orElse("nothing"), topping);
    }

}
