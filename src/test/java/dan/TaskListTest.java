package dan;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class TaskListTest {

    @Test
    public void showTasks_emptyList_exceptionThrown() {
        try {
            List<Task> tasks = new ArrayList<Task>();
            new TaskList(tasks).showTasks();
            fail();
        } catch (DanException e) {
            assertEquals("Oh no! We ran into a problem :(\n"
                    + "Your list is empty!", e.getMessage());
        }
    }

    @Test
    public void markTask_singleTask_success() {
        List<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task("test mark"));
        try {
            TaskList tl = new TaskList(tasks);
            tl.markTask(1);
            assertEquals(true, tl.getTasks().get(0).isDone);
        } catch (DanException e) {
            fail();
        }
    }

    @Test
    public void unMarkTask() {
        List<Task> tasks = new ArrayList<Task>();
        Task task = new Task("test unmark");
        task.setDone(true);
        tasks.add(task);
        try {
            TaskList tl = new TaskList(tasks);
            tl.unMarkTask(1);
            assertEquals(false, tl.getTasks().get(0).isDone);
        } catch (DanException e) {
            fail();
        }
    }

    @Test
    public void deleteTask() {
        List<Task> tasks = new ArrayList<Task>();
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
    public void addTask_allTaskTypes_success() {
        List<Task> tasks = new ArrayList<Task>();
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