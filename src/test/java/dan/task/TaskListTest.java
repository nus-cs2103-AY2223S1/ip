package dan.task;

import dan.exceptions.DanException;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

class TaskListTest {

    @Test
    public void showTasks_emptyList_exceptionThrown() {
        try {
            List<Task> tasks = new ArrayList<>();
            new TaskList(tasks).showTasks();
            fail();
        } catch (DanException e) {
            assertEquals("Oh no! We ran into a problem :(\n"
                    + "Your list is empty!", e.getMessage());
        }
    }

    @Test
    public void markTask_singleTask_success() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("test mark"));
        try {
            TaskList tl = new TaskList(tasks);
            tl.markTask(1);
            assertTrue(tl.getTasks().get(0).isDone);
        } catch (DanException e) {
            fail();
        }
    }

    @Test
    public void markTask_noTaskAtIndex_exceptionThrown() {
        List<Task> tasks = new ArrayList<>();
        Task task = new Task("test unmark");
        task.setDone(true);
        tasks.add(task);
        try {
            TaskList tl = new TaskList(tasks);
            tl.markTask(2);
            fail();
        } catch (DanException e) {
            assertEquals("Oh no! We ran into a problem :(\n"
                    + "This task number doesn't exist!", e.getMessage());
        }
    }

    @Test
    public void unmarkTask_singleTask_success() {
        List<Task> tasks = new ArrayList<>();
        Task task = new Task("test unmark");
        task.setDone(true);
        tasks.add(task);
        try {
            TaskList tl = new TaskList(tasks);
            tl.unmarkTask(1);
            assertFalse(tl.getTasks().get(0).isDone);
        } catch (DanException e) {
            fail();
        }
    }

    @Test
    public void unmarkTask_noTaskAtIndex_exceptionThrown() {
        List<Task> tasks = new ArrayList<>();
        Task task = new Task("test unmark");
        task.setDone(true);
        tasks.add(task);
        try {
            TaskList tl = new TaskList(tasks);
            tl.unmarkTask(2);
            fail();
        } catch (DanException e) {
            assertEquals("Oh no! We ran into a problem :(\n"
                    + "This task number doesn't exist!", e.getMessage());
        }
    }

    @Test
    public void deleteTask_singleTask_success() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("test delete"));
        TaskList tl = new TaskList(tasks);
        assertEquals(1, tl.getTasks().size());
        try {
            tl.deleteTask(1);
            assertEquals(0, tl.getTasks().size());
        } catch (DanException e) {
            fail();
        }
    }

    @Test
    public void deleteTask_noTaskAtIndex_exceptionThrown() {
        List<Task> tasks = new ArrayList<>();
        Task task = new Task("test unmark");
        task.setDone(true);
        tasks.add(task);
        try {
            TaskList tl = new TaskList(tasks);
            tl.deleteTask(2);
            fail();
        } catch (DanException e) {
            assertEquals("Oh no! We ran into a problem :(\n"
                    + "This task number doesn't exist!", e.getMessage());
        }
    }

    @Test
    public void addTask_allTaskTypes_success() {
        List<Task> tasks = new ArrayList<>();
        ToDo t_task = new ToDo("test todo");
        Event e_task = new Event("test event", "20/04/1999 1200");
        Deadline d_task = new Deadline("test deadline", "20/04/1999 1200");
        tasks.add(t_task);
        tasks.add(e_task);
        tasks.add(d_task);
        TaskList tl = new TaskList(tasks);
        assertEquals(t_task, tl.getTasks().get(0));
        assertEquals(e_task, tl.getTasks().get(1));
        assertEquals(d_task, tl.getTasks().get(2));
    }
}