package duke;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class TaskListTest {

    @Test
    public void addToList_threeTask_success() {
        TaskList t = new TaskList(new ArrayList<>());
        ToDo todo = new ToDo("nap");
        Deadline deadline = new Deadline("return book", "12/12/2022");
        Event event = new Event("yoga", "03/09/2022");
        t.addToList(todo);
        t.addToList(deadline);
        t.addToList(event);
        assertEquals(3, t.getSize());
    }

    @Test
    public void deleteTask_twoTask_success() throws DukeException {
        TaskList t = new TaskList(new ArrayList<>());
        ToDo todo = new ToDo("nap");
        Deadline deadline = new Deadline("return book", "12/12/2022");
        Event event = new Event("yoga", "03/09/2022");
        t.addToList(todo);
        t.addToList(deadline);
        t.addToList(event);

        t.deleteTask(1);
        t.deleteTask(2);
    }

    @Test
    public void deleteTask_emptyList_exceptionThrown() {
        TaskList t = new TaskList(new ArrayList<>());
        assertThrows(DukeException.class, () -> t.deleteTask(1));
    }

    @Test
    public void deleteTask_negativeTaskNumber_exceptionThrown() {
        TaskList t = new TaskList(new ArrayList<>());
        assertThrows(DukeException.class, () -> t.deleteTask(-1));
    }

    @Test
    public void list_oneTask_success() {
        TaskList t = new TaskList(new ArrayList<>());
        Event event = new Event("yoga", "12/12/2022");
        t.addToList(event);
        String expected = "\tHere are the tasks in your list :D" + "\n\t1. " + event;
        assertEquals(expected, t.list());
    }

    @Test
    public void getTasks_twoTasksWithDifferentDates_success() {
        TaskList t = new TaskList(new ArrayList<>());
        Event event = new Event("yoga", "12/12/2022");
        Deadline deadline = new Deadline("submit report", "13/12/2022");
        t.addToList(event);
        t.addToList(deadline);
        String expected = "\tYour tasks for today include:" + String.format("\n\t1. %s", deadline);
        assertEquals(expected, t.getTasks("13/12/2022"));
    }
}
