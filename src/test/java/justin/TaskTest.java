package justin;

import justin.task.Deadline;
import justin.task.Task;
import justin.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void taskTest1() {
        Task task = new ToDo("homework", true);
        task.unmark();
        assertEquals("T | Undone | homework", task.toString());
    }

    @Test
    public void taskTest2() {
        Task task = new Deadline("assignment", false, "2022-08-09", "22:00");
        task.mark();
        assertEquals("D | Done! | assignment | Aug 9 2022 1000PM", task.toString());
    }
}
