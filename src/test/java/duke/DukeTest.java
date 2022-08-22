package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.TaskList;

public class DukeTest {
    @Test
    public void addTodo_descriptionToDo_success() {
        // Test description = "Test todo for Duke !"
        String expected = "";
        String actual = "";
        try {
            TaskList taskList = new TaskList();
            Task todo = new ToDoStub();
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
        // Test description = "Finish Assignment 1";
        String dateTime = "16 Aug 2023 1955";
        DateTimeFormatter dateTimeFormatter = DateHandler.getDateTimeFormatter();
        String expected = "";
        String actual = "";
        try {
            TaskList taskList = new TaskList();
            Task deadline = new DeadlineStub(LocalDateTime.parse(dateTime, dateTimeFormatter));
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
        // Test description = "Dinner Date"
        String dateTime = "25 Dec 2029 1835";
        DateTimeFormatter dateTimeFormatter = DateHandler.getDateTimeFormatter();
        String expected = "";
        String actual = "";
        try {
            TaskList taskList = new TaskList();
            Task event = new EventStub(LocalDateTime.parse(dateTime, dateTimeFormatter));
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
