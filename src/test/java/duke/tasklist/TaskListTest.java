package duke.tasklist;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addTaskTest() {
        TaskList list = new TaskList();
        Task newTask = new Todo("a new todo task");
        list.addTaskToList(newTask);
        assertEquals(list.getSize(), 1);
    }

    @Test
    public void writeStringTest() {
        TaskList list = new TaskList();
        Task newTask = new Todo("a new todo task");
        list.addTaskToList(newTask);
        newTask = new Deadline("a new deadline", "01-09-2000 00:00");
        list.addTaskToList(newTask);
        newTask = new Event("a new event", "06-10-1999 00:00");
        list.addTaskToList(newTask);
        assertEquals(list.getWriteString(), "TODO,,false,,a new todo task,,null\n" +
                "DEADLINE,,false,,a new deadline,,01-09-2000 00:00\n" +
                "EVENT,,false,,a new event,,06-10-1999 00:00\n");
    }

    @Test
    public void toStringTest() {
        TaskList list = new TaskList();
        Task newTask = new Todo("a new todo task");
        list.addTaskToList(newTask);
        newTask = new Deadline("a new deadline", "01-09-2000 00:00");
        list.addTaskToList(newTask);
        newTask = new Event("a new event", "06-10-1999 00:00");
        list.addTaskToList(newTask);
        assertEquals(list.toString(), "Here are the tasks that you have added to the list:\n" +
                "1. [T][ ] a new todo task\n" +
                "2. [D][ ] a new deadline (by: Sep 01 2000 00:00)\n" +
                "3. [E][ ] a new event (on: Oct 06 1999 00:00)");
    }

    // TODO Testing for creation, update, deletion of tasks
}
