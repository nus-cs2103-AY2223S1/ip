package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void taskTest() {
        Task t = new Task("something");
        assertEquals(t.toString(), "[T] [ ] something");
    }

    @Test
    public void taskTest2() {
        Task t = new Task("something");
        t.mark();
        assertEquals(t.toString(), "[T] [x] something");
    }

    @Test
    public void taskTest3() {
        Task t = new Task("something");
        t.mark();
        t.unmark();
        assertEquals(t.toString(), "[T] [ ] something");
    }
}
