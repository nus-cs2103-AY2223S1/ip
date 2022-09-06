package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;
import duke.ui.Ui;


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
        Ui ui = new Ui();
        String expected = "Here is the task in your list :D\n\n" + "1. " + event;
        assertEquals(expected, ui.printList(t.list()));
    }

    @Test
    public void list_twoTasksWithDifferentDates_success() {
        TaskList t = new TaskList(new ArrayList<>());
        Event event = new Event("yoga", "12/12/2022");
        Deadline deadline = new Deadline("submit report", "13/12/2022");
        LocalDate date = LocalDate.parse("13/12/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Ui ui = new Ui();
        t.addToList(event);
        t.addToList(deadline);
        String expected = "Your task for 13 Dec 2022 include:" + String.format("\n\n1. %s", deadline);
        assertEquals(expected, ui.printList(t.list("13/12/2022"), date));
    }
}
