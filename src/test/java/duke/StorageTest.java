package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class StorageTest {
    @Test
    public void errorTest(){
        assertThrows(DukeException.class, () -> {
            new Storage("", "").load();
        });
    }

    @Test
    public void loadTest(){
        assertDoesNotThrow(() -> {
            new Storage(Duke.FILE_LOCATION, Duke.FOLDER_LOCATION);
        });
    }
}
