package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class TaskTest {

    @Test
    void getStatusIcon() {
        assertEquals(" ", new Task("test").getStatusIcon());
    }

    @Test
    void mark() {
        Task task = new Task("test");
        task.mark();
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    void unmark() {
        Task task = new Task("test");
        task.mark();
        task.unmark();
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    void testToString() {
        assertEquals("[ ] test", new Task("test").toString());
    }
}
