package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.ui.Ui;

public class UiTest {

    @Test
    public void printList_noTaskAdded_success() {
        Ui ui = new Ui();
        assertEquals("There are no tasks added!", ui.printList(new ArrayList<>()));
    }

    @Test
    public void printList_withTasksAdded_success() {
        ArrayList<Task> t = new ArrayList<>();
        Ui ui = new Ui();
        ToDo toDo = new ToDo("read book");
        Deadline deadline = new Deadline("submit ip", "16/09/2022");
        t.add(toDo);
        t.add(deadline);
        String expected = "Here are the tasks in your list :D\n\n" + "1. " + toDo + "\n2. " + deadline;
        assertEquals(expected, ui.printList(t));
    }

    @Test
    public void printList_tasksOnASpecificDate_success() {
        ArrayList<Task> t = new ArrayList<>();
        Ui ui = new Ui();
        Deadline deadline = new Deadline("submit ip", "16/09/2022");
        t.add(deadline);
        String expected = "Your task for 16 Sep 2022 include:\n" + "\n1. " + deadline;
        LocalDate date = LocalDate.parse("16/09/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String actual = ui.printList(t, date);
        assertEquals(expected, actual);
    }

    @Test
    public void printFind_noTasksAdded_success() {
        Ui ui = new Ui();
        assertEquals("No matching tasks!", ui.printFind(new ArrayList<>()));
    }

    @Test
    public void printFind_tasksAdded_success() {
        ArrayList<Task> t = new ArrayList<>();
        Ui ui = new Ui();
        Deadline deadline = new Deadline("movie report", "16/09/2022");
        Event event = new Event("movie with jane", "20/09/2022");
        t.add(deadline);
        t.add(event);
        String expected = "Here are the matching tasks in your list:\n" + "\n1. "
                + deadline + "\n2. " + event;
        assertEquals(expected, ui.printFind(t));
    }

    @Test
    public void printDelete_oneTaskDeleted_success() {
        Ui ui = new Ui();
        ToDo deleted = new ToDo("read book");
        String expected = "Noted, I have removed this task:\n\n" + deleted
                + "\n\nNow you have 0 task in the list!";
        assertEquals(expected, ui.printDeleteTask(deleted, 0));
    }

    @Test
    public void printMarkTask_markOneTask_success() {
        Ui ui = new Ui();
        Deadline deadline = new Deadline("do more testing", "14/09/2022");
        deadline.markAsDone();
        String expected = "Nice! I have marked this task as done:\n\n" + deadline;
        assertEquals(expected, ui.printMarkTask(deadline));
    }

    @Test
    public void printUnmarkTask_markOneTask_success() {
        Ui ui = new Ui();
        Deadline deadline = new Deadline("do more testing", "14/09/2022");
        deadline.markAsDone();
        deadline.markAsNotDone();
        String expected = "Okay! I have marked this task as not done:\n\n" + deadline;
        assertEquals(expected, ui.printUnmarkTask(deadline));
    }
}
