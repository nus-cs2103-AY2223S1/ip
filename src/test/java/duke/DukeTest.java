package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.TaskList;

public class DukeTest {
    /**
     * Test the adding of a ToDo task to TaskList.
     */
    @Test
    public void addTodo_descriptionToDo_success() {
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

    /**
     * Test the adding of a Deadline task to TaskList
     */
    @Test
    public void addDeadline_descriptionDeadlineLocalDateTime_success() {
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

    /**
     * Test the adding of an Event to TaskList
     */
    @Test
    public void addEvent_descriptionEventLocalDateTime_success() {
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
