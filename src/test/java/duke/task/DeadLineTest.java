package duke.task;

import duke.dukeexception.DukeException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadLineTest {
    @Test
    public void testPrintedDeadline() {
        try {
            assertEquals("[D][ ] return book  (by: 2/2/2022)",
                    new DeadLine("d return book by 2/2/2022").printTask());
            assertEquals("[D][ ] return book  (by: today)",
                    new DeadLine("ddl return book by today").printTask());
        } catch (DukeException e) {
            fail();
        }
    }
}
