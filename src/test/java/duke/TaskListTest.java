package duke;
import duke.tasks.Task;
import duke.tasks.TodoTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void initialLength_emptyList_lengthIsZero() {
        TaskList tasks = new TaskList();
        Task newTask = new TodoTask("todoTask under test");
        assertEquals(tasks.getSize(), 0);
    }

    @Test
    public void addTask_emptyList_lengthIncrease() {
        TaskList tasks = new TaskList();
        Task newTask = new TodoTask("todoTask under test");
        tasks.add(newTask);
        assertEquals(tasks.getSize(), 1);
    }

    @Test
    public void removeTask_taskList_lengthDecrease() {
        TaskList tasks = new TaskList();
        Task newTask = new TodoTask("todoTask under test");
        tasks.add(newTask);
        tasks.remove(0);
        assertEquals(tasks.getSize(), 0);
    }
}
