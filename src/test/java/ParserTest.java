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

}
