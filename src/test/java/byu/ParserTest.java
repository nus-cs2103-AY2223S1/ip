package byu;


import static org.junit.jupiter.api.Assertions.assertEquals;

import byu.util.Parser;
import org.junit.jupiter.api.Test;

import byu.exceptions.ByuException;
import byu.exceptions.IncorrectFileInputException;
import byu.task.ToDo;

public class ParserTest {

    @Test
    public void parse_emptyDescription_exceptionThrown() {
        try {
            Parser.parse("todo");
        } catch (ByuException e) {
            assertEquals(e.getMessage(), "Description of a TODO cannot be empty!");
        }
    }

    @Test
    public void parseFileToTask_success() throws IncorrectFileInputException {
        ToDo task = new ToDo("jump");
        assertEquals(task.getName(), Parser.parseFileToTask("T | 0 | jump").getName());
    }

    @Test
    public void parseFileToTask_invalidInput_exceptionThrown() {
        try {
            Parser.parseFileToTask("A | 1 | jump");
        } catch (IncorrectFileInputException e) {
            assertEquals("Incorrect input from file!", e.getMessage());
        }
    }
}
