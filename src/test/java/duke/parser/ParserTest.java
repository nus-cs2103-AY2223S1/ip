package duke.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;

public class ParserTest {
    @Test
    public void byeTest() {
        String result = "";
        try {
            result = Parser.feedDuke("bye", new TaskList());
        } catch (DukeException e) {
            System.out.println("Valid input failed.");
        }
        assertEquals("Bye. Hope to see you again soon!", result);
    }
}