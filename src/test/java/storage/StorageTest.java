package storage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;


public class StorageTest {

    private Storage emptyTaskFile = new Storage("src/test/java/data/emptyTaskFile.txt");

    @Test
    public void storage_filePathValid_newFileIsCreated() {
        String filePath = "src/test/java/data/testFile.txt";
        Storage s = new Storage(filePath);
        Path p = Path.of(filePath);
        assertTrue(Files.exists(p));
    }

}
