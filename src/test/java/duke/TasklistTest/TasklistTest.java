package duke.TasklistTest;

import duke.models.Event;
import duke.models.Task;
import duke.models.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasklistTest {
    Task newEvent = new Event("this is a test ", "2022-08-31");
    TaskList newTaskList = new TaskList();

    @Test
    public void TaskListTest() {
        newTaskList.add(newEvent);
        assertEquals("[E][ ] this is a test (at: Aug 31 2022)", newTaskList.get(0).toString());
    }
}

