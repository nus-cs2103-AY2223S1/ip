package skylark.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import org.junit.jupiter.api.Test;

import skylark.skylark.SkylarkException;

public class TaskListTest {

    @Test
    public void instantiate_nonExistFile_success() {
        String filePath = System.getProperty("user.dir")
                + File.separator + "test" + File.separator + "empty.txt";
        TaskList taskList = new TaskList(filePath);
        assertEquals(0, taskList.size());
    }

    @Test
    public void instantiate_existFile_success() {
        String filePath = System.getProperty("user.dir")
                + File.separator + "test" + File.separator + "result.txt";
        TaskList taskList = new TaskList(filePath);
        assertEquals(6, taskList.size());
    }

    @Test
    public void get_existFile_success() {
        String filePath = System.getProperty("user.dir")
                + File.separator + "test" + File.separator + "result.txt";
        TaskList taskList = new TaskList(filePath);
        Task currentTask = taskList.get(0);
        assertEquals("Make Coffee", currentTask.getDescription());
    }

    @Test
    public void add_existFile_success() {
        String filePath = System.getProperty("user.dir")
                + File.separator + "test" + File.separator + "result.txt";
        TaskList taskList = new TaskList(filePath);
        Task currentTask = new ToDo("Test Add!");
        taskList.add(currentTask);
        assertEquals("Test Add!", taskList.get(taskList.size() - 1).getDescription());
    }

    @Test
    public void remove_existFile_success() {
        String filePath = System.getProperty("user.dir")
                + File.separator + "test" + File.separator + "result.txt";
        TaskList taskList = new TaskList(filePath);
        assertEquals(6, taskList.size());
        taskList.remove(0);
        taskList.remove(0);
        assertEquals(4, taskList.size());
    }

    @Test
    public void doesIndexExist_existFile_success() {
        String filePath = System.getProperty("user.dir")
                + File.separator + "test" + File.separator + "result.txt";
        TaskList taskList = new TaskList(filePath);
        assertTrue(taskList.doesIndexExist(0));
        assertFalse(taskList.doesIndexExist(taskList.size() + 1));
    }

    @Test
    public void saveToFile_existFile_success() {
        String filePath = System.getProperty("user.dir")
                + File.separator + "test" + File.separator + "result.txt";
        TaskList taskList = new TaskList(filePath);
        try {
            taskList.saveToFile();
        } catch (SkylarkException skylarkException) {
            fail();
        }
    }

    @Test
    public void saveToFile_directory_exceptionThrown() {
        String filePath = System.getProperty("user.dir")
                + File.separator + "test";
        TaskList taskList = new TaskList(filePath);
        try {
            taskList.saveToFile();
            fail();
        } catch (SkylarkException skylarkException) {
            String result = "IOException occurred when writing to file";
            assertEquals(result, skylarkException.getMessage());
        }
    }
}
