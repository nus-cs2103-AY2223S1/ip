package duke.task;

import duke.exception.EmptyDescException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void standardTest1() {
        String desc = " Test1";
        String expected = "[T][ ] Test1";

        try {
            ToDo task = new ToDo(desc);
            assertEquals(expected, task.toString());
        } catch (EmptyDescException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void standardTest2() {
        String desc = " Test2";
        String expected = "T | 0 | Test2";

        try {
            ToDo task = new ToDo(desc);
            assertEquals(expected, task.toSave());
        } catch (EmptyDescException e) {
            System.out.println(e.getMessage());
        }
    }
}