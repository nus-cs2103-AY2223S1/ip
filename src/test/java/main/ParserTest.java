package main;

import org.junit.jupiter.api.Test;

import exception.MeowerException;
import exception.InvalidCommandException;
import meower.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void descriptionTest(){
        Parser p = new Parser();
        try {
            assertEquals(p.parse("todo run").getTask().getDescription(), "run");
        } catch (InvalidCommandException e) {
            return;
        } catch (MeowerException e) {
            return;
        }
    }

    /*@Test
    public void anotherDummyTest(){
        assertEquals(4, 4);
    } */
}   

