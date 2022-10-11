package duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void getTest() throws IOException {
        Task test = new Task("Finish CS2103t iP");
        TaskList taskList = new TaskList();
        taskList.add(test);
        assertEquals(test, taskList.get(0));
    }
}

