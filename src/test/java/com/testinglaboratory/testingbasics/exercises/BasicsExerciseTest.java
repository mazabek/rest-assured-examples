package com.testinglaboratory.testingbasics.exercises;

import com.testinglaboratory.testingbasics.examples.MyData;
import com.testinglaboratory.testingbasics.examples.Pie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/*TODO EXERCISE create tests checking:
   - number of letters in your first name
   - equality of length of your first and last name
   - equality of your first and last name
   - your first name having more than 3 letters
 */

public class BasicsExerciseTest {
    MyData myData;
    @BeforeEach
    public void fillData(){
        myData = new MyData("Małgorzata", "Ząbek");
    }
    @Test
    public void shouldBeTenLetters(){
        assertEquals(10,myData.getFirstName().length());
    }
    @Test
    public void shouldBeEquality1(){
        assertEquals(myData.getLastName().length(),myData.getFirstName().length());
    }
    @Test
    public void shouldBeEquality2(){
        assertEquals(myData.getLastName(),myData.getFirstName());
    }
    @Test
    public void shouldHaveMoreThanThreeLetters(){
        assertThat(myData.getFirstName().length()).isGreaterThan(3);
    }
}
