package duke.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Storage;
import duke.TaskList;

import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ByeCommandTest {
    private static Storage storage;
    private static TaskList tasks;

    @BeforeAll
    public static void createFile() {
        storage = new Storage(Paths.get("dukeList_Test.txt"));
        tasks = new TaskList(new ArrayList<>(), storage);
    }

    @Test
    public void execute_byeCommand_success() {
        ByeCommand command = new ByeCommand();
        assertEquals("Goodbye! Looking forward to see you again!", command.execute(storage, tasks));
    }
}
