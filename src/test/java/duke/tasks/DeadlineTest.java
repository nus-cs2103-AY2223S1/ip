package duke.tasks;

import duke.dukeExceptions.DukeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    
    @Test
    public void test_save_string() throws DukeException {
        Deadline deadline = new Deadline("Test deadline", "2019-09-09 1330");
        String saveString = "D | 0 | Test deadline | true | 2019-09-09 | 1330";
        assertEquals(deadline.saveString(), saveString);
    }
    
    @Test
    public void test_parse_saved_string() throws DukeException {
        String saveString = "D | 0 | Test deadline | true | 2019-09-09 | 1330";
        Deadline deadline = Deadline.taskFromSave(saveString);
        String deadlineString = "[D][ ] Test deadline (by: Sep 9 2019 13:30)";
        assertEquals(deadline.toString(), deadlineString);
    }
}
