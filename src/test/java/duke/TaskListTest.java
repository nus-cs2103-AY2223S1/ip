package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

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
        assertEquals(1, t.getSize());
    }

    @Test
    public void list_tasksWithDifferentDates_success() {
        TaskList t = new TaskList(new ArrayList<>());
        Event event = new Event("yoga", "12/12/2022");
        Deadline deadline = new Deadline("submit report", "13/12/2022");
        t.addToList(event);
        t.addToList(deadline);
        assertEquals(2, t.getSize());
    }

    @Test
    public void find_tasksContainingBook_success() {
        TaskList t = new TaskList(new ArrayList<>());
        ToDo toDo = new ToDo("read book");
        Deadline deadline = new Deadline("return book", "20/12/2022");
        t.addToList(toDo);
        t.addToList(deadline);
        assertEquals(2, t.find("book").size());
    }

    @Test
    public void find_tasksContainingMultipleKeywords_success() {
        TaskList t = new TaskList(new ArrayList<>());
        Event event = new Event("watch movie with mary", "12/10/2022");
        t.addToList(event);
        assertEquals(1, t.find("movie", "mary").size());
    }
}
