package duke.task;
import duke.DukeException;
import duke.task.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void deadlineTest1(){
        try {
            Deadline deadline = new Deadline("homework", "2022-09-10 0900");
            assertEquals("[D][ ] homework (by: 10 Sep 2022 09:00AM)", deadline.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void deadlineTest2(){
        try {
            Deadline deadline = new Deadline("homework", "2022-09-10");
            fail();
        } catch (DukeException e) {
            assertEquals("Input your date and time in the format yyyy-MM-dd HHmm!"
                    , e.getMessage());
        }
    }

    @Test
    public void deadlineTest3(){
        try {
            Deadline deadline = new Deadline("housework", "2022-10-10 0800");
            assertEquals("[D][ ] housework (by: 10 Oct 2022 08:00AM)", deadline.toString());
        } catch (DukeException e) {
            fail();
        }
    }
}
