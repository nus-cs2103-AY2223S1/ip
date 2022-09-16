package duke.utils;

import duke.tasks.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskParserTest {

    @Test
    public void parseTodoTest() throws Exception {
        String str = "todo new todo 1";
        Task todo = TaskParser.stringToTask(TaskType.TODO, str);
        assertTrue(todo instanceof Todo);
    }

    @Test
    public void parseEventTest() throws Exception {
        String str = "event new event 1 /at 2022-01-02";
        Task todo = TaskParser.stringToTask(TaskType.EVENT, str);
        assertTrue(todo instanceof Event);
    }

    @Test
    public void parseDeadlineTest() throws Exception {
        String str = "deadline new deadline 1 /by 2022-01-02";
        Task todo = TaskParser.stringToTask(TaskType.DEADLINE, str);
        assertTrue(todo instanceof Deadline);
    }

}
