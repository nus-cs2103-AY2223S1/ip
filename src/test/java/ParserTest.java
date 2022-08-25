import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import command.FindCommand;
import exceptions.DukeException;
import stubs.StorageStub;
import stubs.TaskListStub;
import stubs.UiStub;
import utility.Parser;


public class ParserTest {
    private TaskListStub taskListStub = new TaskListStub();
    private UiStub uiStub = new UiStub();
    private StorageStub storageStub = new StorageStub(Paths.get("tasksStub.txt"));

    @Test
    public void findCommand_plainString_plainStringReturned() {
        try {
            FindCommand findCommand = new FindCommand();
            assertEquals(true, Parser.parse("find notebook") instanceof FindCommand);
        } catch (DukeException d) {
            fail();
        }
    }

    @Test
    public void getTaskNumber_taskNumberDoesNotExist_dukeExceptionThrown() {
        try {
            int n = Parser.getTaskNumber("1", 0);
            fail();
        } catch (DukeException d) {
            assertEquals(d.getMessage(), "DUKEERROR: task does not exist in list");
        }
    }
}
