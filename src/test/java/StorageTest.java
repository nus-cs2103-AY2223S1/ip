import duke.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
     * @throws DukeException
     */
    @Test
    public void testLoadSavedFile2() throws DukeException {
        Storage storage = new Storage("src/test/java/test.txt");
        TaskList task = storage.loadFile(new TaskList());
        String deadline = "[D][X] test (by: DECEMBER 12 2022 10:55)";
        assertEquals(deadline, task.taskListToArray()[1].toString());

    }
}
