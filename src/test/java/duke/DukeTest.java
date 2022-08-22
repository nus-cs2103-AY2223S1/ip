package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class DukeTest {
    @Test
    public void addTodo_descriptionToDo_success() {
        String descToDo = "Test todo for Duke !";
        String expected = "";
        String actual = "";
        try {
            TaskList taskList = new TaskList();
            Task todo = new ToDo(descToDo);
            expected = generateExpectedOutput(todo);
            taskList.addTaskToList(todo);
            actual = taskList.getAddedTaskOutput();
        } catch (Exception e) {
            actual = e.getMessage();
        }

        assertEquals(expected, actual);
    }

    @Test
    public void addDeadline_descriptionDeadlineLocalDateTime_success() {
        String descDeadline = "Finish Assignment 1";
        String dateTime = "16 Aug 2023 1955";
        DateTimeFormatter dateTimeFormatter = DateHandler.getDateTimeFormatter();
        String expected = "";
        String actual = "";
        try {
            TaskList taskList = new TaskList();
            Task deadline = new Deadline(descDeadline, LocalDateTime.parse(dateTime, dateTimeFormatter));
            expected = generateExpectedOutput(deadline);
            taskList.addTaskToList(deadline);
            actual = taskList.getAddedTaskOutput();
        } catch (Exception e) {
            actual = e.getMessage();
        }

        assertEquals(expected, actual);
    }

    @Test
    public void addEvent_descriptionEventLocalDateTime_success() {
        String descEvent = "Dinner Date";
        String dateTime = "25 Dec 2029 1835";
        DateTimeFormatter dateTimeFormatter = DateHandler.getDateTimeFormatter();
        String expected = "";
        String actual = "";
        try {
            TaskList taskList = new TaskList();
            Task event = new Event(descEvent, LocalDateTime.parse(dateTime, dateTimeFormatter));
            expected = generateExpectedOutput(event);
            taskList.addTaskToList(event);
            actual = taskList.getAddedTaskOutput();
        } catch (Exception e) {
            actual = e.getMessage();
        }

        assertEquals(expected, actual);
    }

    private String generateExpectedOutput(Task task) {
        return String.format("Got it. I've added this task:\n  %s\n%s\n",
                task, String.format("Now you have %d tasks in the list", 1));
    }
}
