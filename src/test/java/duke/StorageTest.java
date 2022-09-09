package duke;

import duke.storage.StorageReader;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void fileLineToTaskTest() {
        Task test = StorageReader.fileLineToTask("D | 1 | return book | 2022-08-27");
        assertEquals(test, new Deadline("return book", true, LocalDate.parse("2022-08-27")));

        test = StorageReader.fileLineToTask("T | 0 | read book");
        assertEquals(test, new ToDo("read book", false));
    }
}
