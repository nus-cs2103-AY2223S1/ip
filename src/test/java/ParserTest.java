import command.FindCommand;
import exceptions.DukeException;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import stubs.TaskListStub;
import stubs.StorageStub;
import stubs.UiStub;
import utility.Parser;

public class ParserTest {
    TaskListStub taskListStub = new TaskListStub();
    UiStub uiStub = new UiStub();
    StorageStub storageStub = new StorageStub(Paths.get("tasksStub.txt"));

    @Test
    public void parse_find_command_test() {
        try {
            FindCommand findCommand = new FindCommand();
            assertEquals(true, Parser.parse("find notebook") instanceof FindCommand);
        } catch (DukeException d) {
           fail();
        }
    }

    @Test
    public void parse_get_task_number_test() {
        try {
            int n = Parser.getTaskNumber("1", 0);
            fail();
        } catch (DukeException d) {
            assertEquals(d.getMessage(), "DUKEERROR: task does not exist in list");
        }
    }
}
