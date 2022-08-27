package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addingTasks(){
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("first"));
        taskList.addTask(new Deadline("second", "2019-12-01"));
        taskList.addTask(new Event("third", "2021-12-02"));

        assertEquals(taskList.size(), 3);
        assertEquals(taskList.toString(), "1. [T][ ] first \n" +
                "2. [D][ ] second (by: 2019-12-01) \n" +
                "3. [E][ ] third (at: 2021-12-02)");
    }
}
