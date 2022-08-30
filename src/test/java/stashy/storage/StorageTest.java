package stashy.storage;

import org.junit.jupiter.api.Test;
import stashy.data.exception.StashyException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test class for Storage.
 */
public class StorageTest {
    @Test
    public void readContentTest() {
        String filePath = "src/test/data/test.txt";
        try {
            Storage storage = new Storage(filePath);
            ArrayList<String> loadResult = storage.load();
            assertEquals("Lorem ipsum", loadResult.get(0).strip());
            assertEquals("Dolor sit amet", loadResult.get(1).strip());
            try {
                loadResult.get(2);
                fail();
            } catch (IndexOutOfBoundsException ioobe) {
                assertEquals("Index 2 out of bounds for length 2", ioobe.getMessage());
            }
        } catch (StashyException se) {
            fail();
        }
    }
}