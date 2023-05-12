package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void TaskObjectTest() {
        Task taskObject = new Task("Task 1");
        String actualOutput = "[ ] Task 1";
        assertEquals(taskObject.toString(), actualOutput);
    }
}
