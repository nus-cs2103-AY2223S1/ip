package Duke;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineFormatTest {

    // test 1 : check if deadline is formatted correctly
    @Test
    public void deadlineFormatTest1() throws WagwanException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Deadline("read book ", "2022-08-08"));
        TaskList tasks1 = new TaskList(tasks);
        String expected = "[D][ ] read book (by: 08/08/2022)";
        assertEquals(expected, tasks1.getTasks().get(0).toString());
    }

    // test 2 : check if deadline is formatted correctly
    @Test
    public void deadlineFormatTest2() throws WagwanException {
        String actual = "";
        String expected = "deadline is not formatted properly";
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            tasks.add(new Deadline("read book ", "08/08/2022"));
        } catch (DateTimeParseException e) {
            actual = "deadline is not formatted properly";
        }
        TaskList tasks1 = new TaskList(tasks);
        assertEquals(expected, actual);
    }
}
