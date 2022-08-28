import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.TaskList;
import duke.Todo;

/**
 * To unit test the Storage class.
 */
public class StorageTest {

    /**
     * Tests the case where the specified filepath does not exist.
     */
    @Test
    public void testLoadEmptyFile() {
        TaskList tasklist = new TaskList();
        Todo todo = new Todo("test123");
        tasklist.add(todo);
        Storage storage = new Storage("fake/");
        TaskList loadedFile = storage.loadFile(tasklist);
        assertEquals(tasklist, loadedFile);
    }

    /**
     * Tests if the saved file has been loaded properly into the tasklist.
     */
    @Test
    public void testLoadSavedFile1() {
        Storage storage = new Storage("src/test/java/test.txt");
        TaskList task = storage.loadFile(new TaskList());
        String todo = new Todo("test").toString();
        assertEquals(todo, task.taskListToArray()[0].toString());
    }

    /**
     * Tests if the saved file has been loaded properly into the tasklist.
     */
    @Test
    public void testLoadSavedFile2() {
        Storage storage = new Storage("src/test/java/test.txt");
        TaskList task = storage.loadFile(new TaskList());
        String deadline = "[D][X] test (by: DECEMBER 12 2022 10:55)";
        assertEquals(deadline, task.taskListToArray()[1].toString());
    }
}
