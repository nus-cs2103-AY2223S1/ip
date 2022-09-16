package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void addingTasks() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("first"));
        taskList.addTask(new Deadline("second", "2019-12-01"));
        taskList.addTask(new Event("third", "2021-12-02"));

        assertEquals(taskList.size(), 3);
    }
}
