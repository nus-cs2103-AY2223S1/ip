package duke;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private static Storage storage = null;
    private static TaskList tasks = null;
    private final Ui ui = new Ui();

    public ParserTest() throws IOException {

    }

    @BeforeAll
    public static void createFile() {
        storage = new Storage(Paths.get("dukeList_Test.txt"));
        tasks = new TaskList(new ArrayList<>(), storage);
    }


    @Test
    public void parseCommand_unknownCommand_success() throws IOException, DukeException {
        assertEquals(false, Parser.parse("run", tasks, ui));
    }

    @Test
    public void parseCommand_createTasks_success() throws DukeException, IOException {
        assertEquals(false, Parser.parse("todo Finish Assignment", tasks, ui));

        assertEquals(false, Parser.parse("event Maroon 5 Concert /at 20/02/2022 2000", tasks, ui));

        assertEquals(false, Parser.parse("deadline CS2102 Group Project /by 12/03/2022 2359", tasks, ui));
    }

    @Test
    public void parseCommand_listTasks_success() throws DukeException, IOException {
        assertEquals(false, Parser.parse("list", tasks, ui));
    }

    @Test
    public void parseCommand_exitDuke_success() throws DukeException, IOException {
        assertEquals(true, Parser.parse("bye", tasks, ui));
    }
}
