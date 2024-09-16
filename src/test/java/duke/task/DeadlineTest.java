package duke.task;

import duke.logic.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void dateTest(){
        assertEquals("[D][ ]  a (by: Oct 15 2019)", new Deadline("a", "2019-10-15").toString());
    }
}
