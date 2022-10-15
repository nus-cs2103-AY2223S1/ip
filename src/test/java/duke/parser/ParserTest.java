package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.tasklist.TaskList;

public class ParserTest {
    @Test
    public void byeTest() {
        Parser parser = new Parser(new TaskList());
        String result = "";
        try {
            result = parser.parseInput("bye");
        } catch (DukeException e) {
            System.out.println("Valid input failed.");
        }
        assertEquals("Bye. Hope to see you again soon!", result);
    }
}
