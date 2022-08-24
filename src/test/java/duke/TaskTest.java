package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void dummyTest1() {
        Task task = new ToDo("homework");
        task.mark();
        assertEquals("[T][X] homework", task.toString());
    }

    @Test
    public void dummyTest2() {
        Task task = new Deadline("assignment", new String[]{" ", "2022-12-09", "23:59"});
        task.mark();
        assertEquals("[D][X] assignment (by: Dec 9 2022 11:59PM)", task.toString());
    }
}
