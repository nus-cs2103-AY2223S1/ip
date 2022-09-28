package bob;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parse_invalidTaskRemovalIndex_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            Parser parser = new Parser();
            parser.parse("remove 1", tasks);
        } catch (BobException e) {
            assertEquals("you don't have that many tasks!", e.getMessage());
        }
    }

    @Test
    public void parse_noMarkIndex_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            Parser parser = new Parser();
            parser.parse("mark", tasks);
        } catch (BobException e) {
            assertEquals("which task to mark?", e.getMessage());
        }
    }

    @Test
    public void parse_updateDetailsMissing_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            Parser parser = new Parser();
            parser.parse("update 1 sleep", tasks);
        } catch (BobException e) {
            assertEquals("what part of the task do you want to update?", e.getMessage());
        }
    }

    @Test
    public void parse_updatedDateFormatIncorrect_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            Parser parser = new Parser();
            parser.parse("update 1 /12-12-2020", tasks);
        } catch (BobException e) {
            assertEquals("please input date in yyyy-mm-dd format!", e.getMessage());
        }
    }

    @Test
    public void parse_noFindWord_exceptionThrown() {
        try {
            TaskList tasks = new TaskList();
            Parser parser = new Parser();
            parser.parse("find", tasks);
        } catch (BobException e) {
            assertEquals("what word do you want to search for?", e.getMessage());
        }
    }

}
