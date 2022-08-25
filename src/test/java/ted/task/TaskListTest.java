package ted.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ted.exception.TedException;

public class TaskListTest {
    @Test
    public void testGetSize() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tasks.add(new Todo("read book"));
        }

        assertEquals(0, new TaskList().getSize());
        assertEquals(5, new TaskList(tasks).getSize());
    }

    @Test
    public void testListOutput() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read book"));
        tasks.add(new Deadline("quiz", "2022-12-12 10:30"));
        tasks.add(new Event("lecture", "fri 4-6pm"));
        String output = "Your tasklist:\n" + "1. [T][ ] read book\n"
                + "2. [D][ ] quiz (by: 12 Dec 2022 10:30 AM)\n" + "3. [E][ ] lecture (at: fri 4-6pm)\n";
        assertEquals(output, new TaskList(tasks).list());
    }

    @Test
    public void markTask_indexWithinBounds_success() throws TedException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read book"));
        assertEquals("[T][X] read book", new TaskList(tasks).markTask(1));
    }

    @Test
    public void markTask_indexOutOfBounds_exceptionThrown() {
        try {
            assertEquals("", new TaskList().markTask(1));
            fail();
        } catch (TedException e) {
            assertEquals("Oh no, there's no such task T_T\n", e.getMessage());
        }
    }

    @Test
    public void unmarkTask_indexWithinBounds_success() throws TedException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read book", true));
        assertEquals("[T][ ] read book", new TaskList(tasks).unmarkTask(1));
    }

    @Test
    public void unmarkTask_indexOutOfBounds_exceptionThrown() {
        try {
            assertEquals("", new TaskList().unmarkTask(1));
            fail();
        } catch (TedException e) {
            assertEquals("Oh no, there's no such task T_T\n", e.getMessage());
        }
    }

    @Test
    public void deleteTask_indexWithinBounds_success() throws TedException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read book"));
        assertEquals("[T][ ] read book", new TaskList(tasks).deleteTask(1));
    }

    @Test
    public void deleteTask_indexOutOfBounds_exceptionThrown() {
        try {
            assertEquals("", new TaskList().deleteTask(1));
            fail();
        } catch (TedException e) {
            assertEquals("Oh no, there's no such task T_T\n", e.getMessage());
        }
    }

    @Test
    public void testAddTaskOutput() {
        TaskList tasks = new TaskList();
        assertEquals("[T][ ] read book", tasks.addTask(new Todo("read book")));
    }
}
