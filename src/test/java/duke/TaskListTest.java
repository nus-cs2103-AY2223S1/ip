package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class TaskListTest {

    @Test
    public void addTest() throws DukeException {
        TaskList tasks = new TaskList();
        ArrayList<Task> list = tasks.getTaskArrayList();

        int actualSizeZero = list.size();

        assertEquals(0 , actualSizeZero);

        Todo t = new Todo("go home");
        Deadline d = new Deadline("cs homework", "2022-10-19");
        Event e = new Event("", "2022-10-13");

        tasks.add(t);
        int actualSizeOne = list.size();
        tasks.add(d);
        int actualSizeTwo = list.size();
        tasks.add(e);
        int actualSizeThree = list.size();

        assertEquals(1 , actualSizeOne);
        assertEquals(2 , actualSizeTwo);
        assertEquals(3 , actualSizeThree);

        Task t1 = list.get(0);
        Task t2 = list.get(1);
        Task t3 = list.get(2);

        assertEquals(t, t1);
        assertEquals(d, t2);
        assertEquals(e, t3);
    }

    @Test
    public void deleteTest() throws DukeException {
        TaskList tasks = new TaskList();
        ArrayList<Task> list = tasks.getTaskArrayList();

        Todo t = new Todo("go home");
        Deadline d = new Deadline("cs homework", "2022-10-19");
        Event e = new Event("", "2022-10-13");

        tasks.add(t);
        tasks.add(d);
        tasks.add(e);

        int actualSizeInitial = list.size();
        assertEquals(3, actualSizeInitial);

        tasks.delete(2); // Delete Deadline
        int actualSizeEnd = list.size();
        assertEquals(2, actualSizeEnd);
        Task t1 = list.get(0);
        Task t2 = list.get(1);
        assertEquals(t, t1);
        assertEquals(e, t2);
    }
}
