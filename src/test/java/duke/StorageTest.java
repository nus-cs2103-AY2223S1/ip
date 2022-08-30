package duke;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void errorTest() {
        assertThrows(DukeException.class, () -> {
            new Storage("", "").load();
        });
    }

    @Test
    public void loadTest() {
        assertDoesNotThrow(() -> {
            new Storage(Duke.FILE_LOCATION, Duke.FOLDER_LOCATION);
        });
    }
}
