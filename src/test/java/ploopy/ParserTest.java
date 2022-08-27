package ploopy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.fail;


class ParserTest {
    @Test
    public void parseInput_unsupportedInput_exceptionThrown() {
        try {
            UI ui = new UI();
            Storage storage = new Storage(ui);
            TaskList taskList = new TaskList(ui, storage);
            Parser.parseInput("gibberish", taskList);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("nonsense", e.getMessage());
        }
    }

    @Test
    public void parseInput_blankInput_exceptionThrown() {
        try {
            UI ui = new UI();
            Storage storage = new Storage(ui);
            TaskList taskList = new TaskList(ui, storage);
            Parser.parseInput("          ", taskList);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("blank", e.getMessage());
        }
    }
}