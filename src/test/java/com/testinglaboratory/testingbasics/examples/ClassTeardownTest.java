package com.testinglaboratory.testingbasics.examples;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClassTeardownTest {
    @BeforeAll
    public static void setUpInitialValue(){
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
        Assertions.assertEquals( 7, TheIncrementor.getValue());
    }

    @Test
    @Order(3)
    public void thirdStep(){
        TheIncrementor.increment();
        Assertions.assertEquals( 8, TheIncrementor.getValue());
    }

    @AfterAll
    public static void cleanUp(){
        //It's used to clean up after test - e.g. close the browser if needed
        //or return DB entry to its original state
        //or... resetting reactor IYKWIM ;)
        TheIncrementor.setValue(0);
    }

}
