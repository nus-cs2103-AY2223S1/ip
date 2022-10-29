package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void taskListSizeTest() {
        TaskList testList = new TaskList();
        testList.add(new Todo("todo 1"));
        testList.add(new Todo("todo 2"));
        testList.add(new Todo("todo 3"));
        assertEquals(3, testList.size());
    }

    @Test
    public void taskListGetTest() {
        TaskList testList = new TaskList();
        testList.add(new Todo("todo 1"));
        testList.add(new Todo("todo 2"));
        testList.add(new Todo("todo 3"));
        assertEquals((new Todo("todo 3")).toString(), testList.get(2).toString());
    }
}
