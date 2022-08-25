package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void testFilterDate() {
        TaskList tasks = new TaskList();
        Deadline deadline = new Deadline("test deadline", LocalDate.parse("2022-01-01"));
        Event event = new Event("test event", LocalDate.parse("2022-02-02"));
        tasks.addTask(deadline);
        tasks.addTask(event);
        ArrayList<Task> filteredTasks = tasks.getTasksOn(LocalDate.parse("2022-02-02"));
        assertEquals(filteredTasks.size(), 1);
        assertEquals(filteredTasks.get(0), event);
    }
}
