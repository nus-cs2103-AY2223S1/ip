package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.ToDo;

public class ToDoTest {

    @Test
    public void testToDo() {
        Task testTask = new ToDo("Read book");
        assertEquals(testTask.taskInfo(), "[T] [ ] [LOW] Read book");
    }

    @Test
    public void testMarkToDo() {
        Task testTask = new ToDo("Read book");
        testTask.markAsDone();
        assertEquals(testTask.taskInfo(), "[T] [X] [LOW] Read book");
    }

    @Test
    public void testChangeToDoPriority() {
        Task testTask = new ToDo("Read book");
        testTask.setTaskPriority("high");
        assertEquals(testTask.taskInfo(), "[T] [ ] [HIGH] Read book");
    }

}
