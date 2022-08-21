package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void testFilterDate() {
        TaskList taskList = new TaskList();
        Deadline deadline = new Deadline("test deadline", LocalDate.parse("2022-01-01"));
        Event event = new Event("test event", LocalDate.parse("2022-02-02"));
        taskList.addTask(deadline);
        taskList.addTask(event);
        ArrayList<Task> filteredTaskList = taskList.getTasksOn(LocalDate.parse("2022-02-02"));
        assertEquals(filteredTaskList.size(), 1);
        assertEquals(filteredTaskList.get(0), event);
    }
}
