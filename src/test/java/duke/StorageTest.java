package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

public class StorageTest {

    @Test
    public void testStorage() throws DukeException {
        Storage storage = new Storage("mytasklist.txt");

        assertEquals(storage.load().toString(),
                "[T,0,haha \n, T,1,booooooom 2020-12-12 1222 \n, "
                        + "E,1,wowwww ,2020-03-03,23:23\n, D,1,shagggg ,2011-01-01,00:00\n]");
    }
}
