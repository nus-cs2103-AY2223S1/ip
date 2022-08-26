package seedu.duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addTaskTest()  throws DukeException{
        TaskList tasklist = new TaskList();
        Event event = new Event("seminar", "blank time");
        tasklist.addTask("event", "event seminar /at blank time");
        assertEquals(event.getDescription(), tasklist.getTaskList().get(0).getDescription());
    }

    // it doesn't seem right that my test for one method should rely on the 1st method being correct first
    @Test
    public void deleteTaskTest() throws DukeException {
        TaskList tasklist = new TaskList();
        Event event = new Event("seminar", "blank time");
        tasklist.addTask("event", "event seminar /at blank time");
        tasklist.addTask("todo", "todo read book");
        tasklist.deleteTask(1);
        assertEquals(1, tasklist.getTaskList().size());
    }
}
