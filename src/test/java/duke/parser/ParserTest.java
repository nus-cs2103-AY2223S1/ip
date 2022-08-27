package duke.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.exception.DukeException;
import duke.tasklist.TaskList;

public class ParserTest {
    @Test
    public void byeTest() {
        Boolean result = false;
        try {
            result = Parser.FeedDuke("bye", new TaskList());
        } catch (DukeException e) {
            System.out.println("Valid input failed.");
        }
        assertTrue(result);
    }
}