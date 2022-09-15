package duke.parser;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DukeParserTest {

    DukeParser parser = new DukeParser(new TaskList());
    Storage st = new Storage("random");

    @Test
    public void throwsExceptionWhenCommandNotLoaded() {
        assertThrows(DukeException.class, () -> parser.execute(st));
    }

    @Test
    public void throwsExceptionWhenEmptyCommand() {
        parser.parseInput("");
        assertThrows(DukeException.class, () -> parser.execute(st));
    }
}
