package duke;

import duke.task.Task;

import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void toDoTest() {
        Task testTask = new ToDo("Read book");
        assertEquals(testTask.taskInfo(), "[T] [ ] Read book");
    }

}
