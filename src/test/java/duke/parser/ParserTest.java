package duke.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.tasklist.TaskList;

public class ParserTest {
    @Test
    public void byeTest() {
        Boolean result = false;
        try {
            result = Parser.parseInput("bye", new TaskList());
        } catch (DukeException e) {
            System.out.println("Valid input failed.");
        }
        assertTrue(result);
    }
}
