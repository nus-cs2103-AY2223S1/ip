package test.stashy.storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void readContentTest() {
        String filePath = "src/test/data/test.txt"
        Storage storage = new Storage(filePath);
        ArrayList<String> loadResult = storage.load();
        assertEquals("Lorem ipsum", loadResult.get(0).strip());
        assertEquals("Dolor sit amet", loadResult.get(1).strip());
        try {
            String inexistentLine = loadResult.get(2);
            fail();
        } catch (IndexOutOfBoundsException ioobe) {
            assertEquals("Index: 2, Size: 2", ioobe.getMessage());
        }
    }
}