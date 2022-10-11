package utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;

import stubs.StorageStub;
import task.Deadline;
import task.Event;
import task.Task;



public class StorageParserTest {
    private StorageStub storageStub = new StorageStub(Paths.get("tasksStub.txt"));
    private String incompleteTaskLongDesc = "Task aviaryOpening is not completed yet";
    private String completedTaskLongDesc = "Task aviaryOpening is completed";

    @Test
    public void fileLineToTask_convertsStringToTask_taskReturned() {
        List<String> lines = storageStub.getAllLines();
        for (int i = 0; i < lines.size(); i++) {
            assert (StorageParser.fileLineToTask(lines.get(i)) instanceof Task);
        }
    }

    @Test
    public void fileLineToTask_emptyLine_returnsNull() {
        assertTrue(StorageParser.fileLineToTask("") == null);
    }

    @Test
    public void fileLineToTask_incompleteTaskLine_returnsNull() {
        assertTrue(StorageParser.fileLineToTask("T[") == null);
    }

    @Test
    public void fileLineToTask_invalidMarkSymbol_returnsUnmarkedTask() {
        Task t = StorageParser.fileLineToTask("[T][9] aviaryOpening");
        assertEquals(incompleteTaskLongDesc, t.longDescription());
    }

    @Test
    public void fileLineToTask_validMarkSymbol_returnsUnmarkedTask() {
        Task t = StorageParser.fileLineToTask("[T][1] aviaryOpening");
        assertEquals(completedTaskLongDesc, t.longDescription());
    }

    @Test
    public void fileLineToTask_markSymbolTooBig_returnsUnmarkedTask() {
        Task t = StorageParser.fileLineToTask("[T][1000] aviaryOpening");
        assertEquals(null, t);
    }

    @Test
    public void fileLineToTask_invalidTypeOfLine_returnsNull() {
        assertTrue(StorageParser.fileLineToTask("[p]") == null);

        assertTrue(StorageParser.fileLineToTask("[  ] description ") == null);
    }

    @Test
    public void fileLineToTask_validTypesOfLines_returnsTaskObject() {
        assertTrue(StorageParser.fileLineToTask("[t][ ] todo") instanceof Task);

        assertTrue(StorageParser.fileLineToTask("[E][ ] aviaryOpening (at: 2022-10-10)") instanceof Event);

        assertTrue(StorageParser.fileLineToTask("[d][ ] aviaryOpening (by: 2022-10-10)") instanceof Deadline);
    }

    @Test
    public void fileLinetoTask_invalidMarkIndicators_returnsNull() {
        //No marking indication given
        assertTrue(StorageParser.fileLineToTask("[t] todo") == null);

        //value contained in isMarked box is not 1 character long
        assertTrue(StorageParser.fileLineToTask("[T][  ] todo") == null);

        //No space left for mark symbol
        assertTrue(StorageParser.fileLineToTask("[T][] todo") == null);

        //Incomplete section
        assertTrue(StorageParser.fileLineToTask("[T][   todo") == null);
    }


}
