import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.Storage;


public class StorageTest {
    private Storage storage = new Storage("D:\\cs2103t\\duke.txt");

    public StorageTest() throws IOException {
    }

    @Test
    public void storageTest() throws IOException {
        storage.push("deadline return book /by 2019-5-29");
        assertEquals("deadline return book /by 2019-5-29", storage.load());
    }
}
