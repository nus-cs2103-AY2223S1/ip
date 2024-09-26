package duke.task;

import duke.exception.EmptyDescException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void standardTest1() {
        String desc = " Test1";
        String by = "2000-12-05";
        String expected = "[D][ ] Test1 (by: Dec 5 2000)";

        try {
            Deadline task = new Deadline(desc, by);
            assertEquals(expected, task.toString());
        } catch (EmptyDescException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void standardTest2() {
        String desc = " Test2";
        String by = "2000-01-01";
        String expected = "D | 0 | Test2 | 2000-01-01";

        try {
            Deadline task = new Deadline(desc, by);
            assertEquals(expected, task.toSave());
        } catch (EmptyDescException e) {
            System.out.println(e.getMessage());
        }
    }
}
