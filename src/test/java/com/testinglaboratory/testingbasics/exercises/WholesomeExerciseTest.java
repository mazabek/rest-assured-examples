package com.testinglaboratory.testingbasics.exercises;

import com.testinglaboratory.testingbasics.examples.MyData;
import com.testinglaboratory.testingbasics.examples.Toy;
import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*TODO EXERCISE
 * - Create class Toy having fields: make, name, colour, material
 *  - having methods returning greeting (including name, colour and material)
 *  - create getters and setters
 *  - write tests that put a text file with toy data to a text file in a folder
 *  - use FileManager to operate on files
 *  - remember to prepare data
 *  - remember to clean up after tests
 */
public class WholesomeExerciseTest {
    Toy toy;
    @BeforeEach
    public void fillData(){
        toy = new Toy("myMake","teddyBear","brown","cotton");
    }
    @Test
    public void createFile(){
        FileManager.createDirectory("toys");
        FileManager.writeToFileFile("toys/teddyBear",toy.greeting());
        assertEquals(toy.greeting(), FileManager.readFile("toys/teddyBear"));

    }
    @AfterAll
    public static void removeFile(){
        FileManager.deleteFile("toys/teddyBear");
    }

}
