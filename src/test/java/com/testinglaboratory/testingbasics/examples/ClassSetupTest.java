package com.testinglaboratory.testingbasics.examples;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClassSetupTest {
    @BeforeAll
    public static void setUpInitialValue(){
        //I could be accessing database here
        //To fetch data needed for ALL of the test cases
        //Either read only OR as a single object available for all cases
        //IMPORTANT!!! static object available to all cases should be used with care
        TheIncrementor.setValue(5);
    }

    @Test
    @Order(1)
    public void firstStep(){
        TheIncrementor.increment();
        Assertions.assertEquals(6, TheIncrementor.getValue());
    }

    @Test
    @Order(2)
    public void secondStep(){
        TheIncrementor.increment();
        Assertions.assertEquals(7, TheIncrementor.getValue());
    }

    @Test
    @Order(3)
    public void thirdStep(){
        TheIncrementor.increment();
        Assertions.assertEquals(8, TheIncrementor.getValue());
    }
}
