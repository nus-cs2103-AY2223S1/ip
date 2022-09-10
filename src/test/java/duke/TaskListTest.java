package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.Todo;

public class TaskListTest {
    @Test
    public void getItemTest() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("buy bread"));
        assertEquals("[T][ ] buy bread", tasks.getItem(1).toString());
    }


    @Test
    public void getListSizeTest() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.getListSize());
    }

    @Test
    public void addTaskTest() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("buy bread"));
        assertEquals(1, tasks.getListSize());
    }

    @Test
    public void deleteTaskTest() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("buy bread"));
        tasks.addTask(new Todo("buy milk"));
        assertEquals(2, tasks.getListSize());
        tasks.deleteTask(1);
        assertEquals(1, tasks.getListSize());
        assertEquals("[T][ ] buy milk", tasks.getItem(1).toString());
    }

    public void markAndUnmarkTaskTest() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("buy bread"));
        tasks.markTask(1);
        assertEquals("[T][X] buy bread", tasks.getItem(1).toString());
        tasks.unmarkTask(1);
        assertEquals("[T][ ] buy bread", tasks.getItem(1).toString());
    }
}
