import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.Storage;


public class StorageTest {

    private File tempFile;
    private Storage storage = new Storage("data/tasks.txt");

    public StorageTest() throws IOException {
    }

    @Test
    public void storageTest() throws IOException {
        storage.push("deadline return book /by 2019-5-29");
        assertEquals("deadline return book /by 2019-5-29\n", storage.load());
    }
    @Test
    void testToString_inputSet_returnsCorrectString() throws IOException {
        Storage storage = new Storage(tempFile.getAbsolutePath());
        String input = "todo read book";
        storage.push(input);
        String expected = "Storage[input=" + input + "]";
        assertEquals(expected, storage.toString());
    }
}
