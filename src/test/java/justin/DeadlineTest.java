package justin;

import justin.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void deadlineTest1() {
        Deadline task = new Deadline("homework", false, "2022-02-03", "22:00");
        assertEquals("D | Undone | homework | Feb 3 2022 1000PM", task.toString());
    }

    @Test
    public void deadlineTest2() {
        Deadline task = new Deadline("homework", false, "2022-02-10", "10:00");
        task.mark();
        assertEquals("D | Done! | homework | Feb 10 2022 1000AM", task.toString());
    }
}
