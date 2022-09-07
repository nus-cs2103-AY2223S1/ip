package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

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

    @Test
    void markDone_indexOutOfBound_throwsException() {
        try {
            String path = "./src/test/data/duke.txt";
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            TaskList taskList = new TaskList(path);
            taskList.markDone(-1);
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid task number.", e.getMessage());
        }
    }

    @Test
    void unmarkDone_indexOutOfBound_throwsException() {
        try {
            String path = "./src/test/data/duke.txt";
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            TaskList taskList = new TaskList(path);
            taskList.unmarkDone(-1);
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid task number.", e.getMessage());
        }
    }
}
