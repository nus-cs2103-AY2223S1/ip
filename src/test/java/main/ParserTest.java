package main;

import org.junit.jupiter.api.Test;

import command.TodoCommand;
import exception.InvalidCommandException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void dummyTest(){
        Parser p = new Parser();
        try {
            assertEquals(p.parse("todo run"), "run");
        } catch (InvalidCommandException e) {
            return;

        }
    }

    @Test
    public void anotherDummyTest(){
        assertEquals(4, 4);
    }
}   

