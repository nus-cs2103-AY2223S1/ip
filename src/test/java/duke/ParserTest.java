package duke;

import duke.task.Deadlines;
import duke.task.Task;
import duke.task.ToDos;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void dataToInfo_ToDosTask_success() {
        assertEquals("[T] hello [ ]", Parser.dataToInfo("T|0|hello|0|").toString());
    }
}
