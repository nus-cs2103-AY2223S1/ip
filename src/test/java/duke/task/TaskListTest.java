package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    private TaskList list = new TaskList();

    @Test
    public void addTaskTest() {
        list.add(new Task("read book", "Z"));
        assertEquals(list.getTask(0).toString(), new Task("read book", "Z").toString());
    }

    @Test
    public void deleteTaskTest() {
        list.add(new Task("read book", "Z"));
        assertEquals(list.getSize(), 1);
        list.delete(0);
        assertEquals(list.getSize(), 0);
    }

    @Test
    public void clearTest() {
        list.add(new Task("borrow book", "Z"));
        list.add(new Task("read book", "Z"));
        list.add(new Task("return book", "Z"));
        assertEquals(list.getSize(), 3);
        list.clear();
        assertEquals(list.getSize(), 0);
    }

    @Test
    public void markUnmarkTaskTest() {
        list.add(new Task("read book", "Z"));
        list.mark(0);
        assertEquals(list.getTask(0).isDone, true);
        list.unmark(0);
        assertEquals(list.getTask(0).isDone, false);
    }
}
