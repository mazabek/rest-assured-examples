package com.testinglaboratory.testingbasics.examples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class BasicsTest {

    @Test
    public void shouldStateThatTrueIsTrue() {
        Boolean myStatement = true;
        assertTrue(myStatement);
    }

    @Test
    public void shouldFail() {
        Boolean myStatement = false;
        assertTrue(myStatement, "Failed because it should be true!");
    }

    @Test
    public void shouldStateThatFalseIsFalse() {
        Boolean myStatement = false;
        assertFalse(false);
    }

    @Test
    public void shouldCheckForEquality() {
        Integer firstInteger = 1;
        Integer secondInteger = 1;
        assertEquals(firstInteger, secondInteger);
    }

    @Test
    public void shouldCheckForDifference() {
        Integer firstInteger = 1;
        Integer secondInteger = 2;
        assertNotEquals(firstInteger, secondInteger);
    }

    @Test
    public void whenAssertingArraysEquality_thenEqual() {
        char[] expected = {'J', 'u', 'n', 'i', 't'};
        char[] actual = "Junit" .toCharArray();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void givenNullArrays_whenAssertingArraysEquality_thenEqual() {
        int[] expected = null;
        int[] actual = null;

        assertArrayEquals(expected, actual);
    }

    @Test
    public void whenAssertingNull_thenTrue() {
        Object car = null;

        assertNull(car, "The car should be null");
    }

    @Test
    public void whenAssertingNull() {
        Object car = null;
        assertNotNull(car, "The car should NOT be null");
    }

    @Test
    public void whenAssertingNotSameObject_thenDifferent() {
        Object cat = new Object();
        Object dog = new Object();

        assertNotSame(cat, dog);
    }

    @Test
    public void whenCheckingExceptionMessage_thenEqual() {
        try {
            fail("Forcefully failed");
        } catch (UnsupportedOperationException e) {
            assertEquals("Operation Not Supported", e.getMessage());
        }
    }

    @Test
    public void givenMultipleAssertion_whenAssertingAll_thenOK() {
        assertAll(
                "Will check for everything",
                () -> assertTrue(false, "Failing me softly"),
                () -> assertEquals(4, 2 * 2, "4 is 2 times 2"),
                () -> assertEquals("java", "JAVA" .toLowerCase()),
                () -> assertEquals("java", "kava", "But actually..."),
                () -> assertNull("tests", "But it should be...")
        );
    }
}
