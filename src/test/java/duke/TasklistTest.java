package duke;

import duke.task.TaskList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.File;
import duke.task.ToDo;

public class TasklistTest {

    @Test
    void getSize_emptyTaskList_returnZero() {
        String path = "./src/test/data/duke.txt";
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        TaskList taskList = new TaskList(path);
        assertEquals(0, taskList.getSize());
    }

    @Test
    void getSize_nonEmptyTaskList_returnsSize() {
        String path = "./src/test/data/duke.txt";
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        TaskList taskList = new TaskList(path);
        taskList.addTask(new ToDo("Test 1"));
        taskList.addTask(new ToDo("Test 2"));
        assertEquals(2, taskList.getSize());
    }
}