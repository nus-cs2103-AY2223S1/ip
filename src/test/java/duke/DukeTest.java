package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
    @Test
    public void addTodoSuccess() {
        String expected = "[T][ ] new book";
        String actual = "";
        try {
            TaskList myTaskList = new TaskList(new ArrayList<>());
            myTaskList.addTask(new Todo("new book"));
            actual = myTaskList.testListTasks();
        } catch (Exception e) {
            actual = e.getMessage();
        }
        assertEquals(expected, actual);
    }

    @Test
    public void addDeadlineSuccess() {
        String expected = "[D][ ] read book (by: May 12 2019)";
        String actual = "";
        try {
            TaskList myTaskList = new TaskList(new ArrayList<>());
            LocalDate curDate = LocalDate.parse("2019-05-12");
            myTaskList.addTask(new Deadline("read book", curDate));
            actual = myTaskList.testListTasks();
        } catch (Exception e) {
            actual = e.getMessage();
        }
        assertEquals(expected, actual);
    }

    @Test
    public void addEventSuccess() {
        String expected = "[E][ ] read newspaper (at: May 12 2022)";
        String actual = "";
        try {
            TaskList myTaskList = new TaskList(new ArrayList<>());
            LocalDate curDate = LocalDate.parse("2022-05-12");
            myTaskList.addTask(new Event("read newspaper", curDate));
            actual = myTaskList.testListTasks();
        } catch (Exception e) {
            actual = e.getMessage();
        }
        assertEquals(expected, actual);
    }

    @Test
    public void deleteTodoSuccess(){
        String expected = "";
        expected += "Noted. I've removed this task:\n";
        expected += "[T][ ] new book";
        expected += "\nNow you have 0 in the list.";
        String actual = "";
        try {
            TaskList myTaskList = new TaskList(new ArrayList<>());
            myTaskList.addTask(new Todo("new book"));
            actual = myTaskList.removeTask(0);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        assertEquals(expected, actual);
    }
}

