package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

//import org.junit.Before;
import org.junit.jupiter.api.Test;



public class TaskListTest {
    /*TaskList taskList = new TaskList();

    @Before
    public void init() {
        List<Task> list = new ArrayList<>();
        list.add(new Task("complete homework"));
        list.add(new Task("walk dog"));
        list.add(new Task("buy dinner"));
        taskList = new TaskList(list);
    }*/

    @Test
    public void listSizeTest() {
        TaskList taskList;
        List<Task> list = new ArrayList<>();
        list.add(new Task("complete homework"));
        list.add(new Task("walk dog"));
        list.add(new Task("buy dinner"));
        taskList = new TaskList(list);
        assertEquals(3, taskList.listSize());
    }
}
