package storage;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
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

    @Test
    public void isLineAppended_appendLine_lineIsAppended() {
        assertTrue(emptyTaskFile.isLineAppended("newLine"));
        Path p = Path.of("src/test/java/data/emptyTaskFile.txt");
        try {
            assertTrue(Files.readAllLines(p).size() != 0);
            Files.writeString(p, "");
        } catch (IOException ioe) {
            fail();
        }
    }

}
