package john.data;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void listTask_noParams_taskReturned() {
        TaskList tl = new TaskList();
        tl.addTodo("hello word");
        tl.addDeadline("goodbye", "12/12/2022");
        tl.addEvent("goodbye", "12/12/2022");
        String[] resultArray = new String[3];
        resultArray[0] = "[T][ ] hello word";
        resultArray[1] = "[D][ ] goodbye (by: Dec 12 2022)";
        resultArray[2] = "[E][ ] goodbye (at: Dec 12 2022)";
        assertArrayEquals(resultArray, tl.listTasks(""));
    }

    @Test
    public void listTask_withParams_taskReturned() {
        TaskList tl = new TaskList();
        tl.addTodo("hello word");
        tl.addDeadline("goodbye", "12/12/2022");
        tl.addEvent("goodbye", "12/12/2022");
        String[] resultArray = {null, "[D][ ] goodbye (by: Dec 12 2022)", "[E][ ] goodbye (at: Dec 12 2022)"};
        assertArrayEquals(resultArray, tl.listTasks("12/12/2022"));
    }

    @Test
    public void findTask_withParams_taskReturned() {
        TaskList tl = new TaskList();
        tl.addTodo("hello word");
        tl.addDeadline("goodbye", "12/12/2022");
        tl.addEvent("goodbye", "12/12/2022");
        String[] resultArray = {null, "[D][ ] goodbye (by: Dec 12 2022)", "[E][ ] goodbye (at: Dec 12 2022)"};
        assertArrayEquals(resultArray, tl.findTasks("bye"));
    }

    @Test
    public void addTodoTest() {
        TaskList tl = new TaskList();
        assertEquals("[T][ ] hello word", tl.addTodo("hello word"));
    }

    @Test
    public void addDeadlineTest() {
        TaskList tl = new TaskList();
        assertEquals("[D][ ] goodbye (by: Dec 12 2022)",
                tl.addDeadline("goodbye", "12/12/2022"));
    }

    @Test
    public void addEventTest() {
        TaskList tl = new TaskList();
        assertEquals("[E][ ] goodbye (at: Dec 12 2022)",
                tl.addEvent("goodbye", "12/12/2022"));
    }

    @Test
    public void markTaskTest() {
        TaskList tl = new TaskList();
        tl.addTodo("hello");
        assertEquals("[T][X] hello", tl.markTask("1"));
    }

    @Test
    public void unmarkTaskTest() {
        TaskList tl = new TaskList();
        tl.addTodo("hello");
        assertEquals("[T][ ] hello", tl.unmarkTask("1"));
    }

    @Test
    public void deleteTaskTest() {
        TaskList tl = new TaskList();
        tl.addTodo("hello");
        assertEquals("[T][ ] hello", tl.deleteTask("1"));
    }

    @Test
    public void getTasksToStoreTest() {
        TaskList tl = new TaskList();
        tl.addTodo("hello word");
        tl.addDeadline("goodbye", "12/12/2022");
        tl.addEvent("goodbye", "12/12/2022");
        ArrayList<String> result = new ArrayList<>();
        result.add("T | 0 | hello word");
        result.add("D | 0 | goodbye | 12/12/2022");
        result.add("E | 0 | goodbye | 12/12/2022");
        assertArrayEquals(result.toArray(), tl.getTasksToStore().toArray());
    }

    @Test
    public void getNumberOfTasksTest() {
        TaskList tl = new TaskList();
        tl.addTodo("hello");
        tl.addTodo("world");
        tl.addTodo("123");
        assertEquals(3, tl.getNumberOfTasks());
    }
}
