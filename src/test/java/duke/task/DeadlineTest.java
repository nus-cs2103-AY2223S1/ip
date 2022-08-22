package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    void saveText_normalInput_unmarked() throws DukeException {
        Task deadline = new Deadline("sample task /by 2022-10-10");
        assertEquals("D|0|sample task /by 2022-10-10", deadline.saveText());
    }

    @Test
    void saveText_normalInput_marked() throws DukeException {
        Task deadline = new Deadline("sample task /by 2022-10-10");
        deadline.mark();
        assertEquals("D|0|sample task /by 2022-10-10", deadline.saveText());
    }

    @Test
    void saveText_noDateInput_marked() {
        try {
            Task deadline = new Deadline("sample task");
            assertEquals("D|0|sample task", deadline.saveText());
            fail();
        } catch (Exception e) {
            assertEquals("You need to use \"/by\" to specify when the event is", e.getMessage());
        }
    }
}
