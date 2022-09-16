package ploopy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ploopy.task.TaskList;

class ParserTest {
    @Test
    public void parseInput_unsupportedInput_exceptionThrown() {
        try {
            Storage storage = new Storage();
            TaskList taskList = new TaskList(storage);
            Parser.parseInput("gibberish", taskList);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("nonsense", e.getMessage());
        }
    }

    @Test
    public void parseInput_blankInput_exceptionThrown() {
        try {
            Storage storage = new Storage();
            TaskList taskList = new TaskList(storage);
            Parser.parseInput("          ", taskList);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("blank", e.getMessage());
        }
    }
}
