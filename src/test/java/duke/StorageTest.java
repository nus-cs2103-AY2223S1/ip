package duke;

import org.junit.jupiter.api.Test;

public class StorageTest {

    @Test
    public void testStorage() {
        Storage s = new Storage("./tests/test.txt");
        try {
            s.load();
        } catch (DukeException e){
        }
    }
}
