package betago.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import betago.DukeException;




public class DeadlineTest {

    @Test
    public void newDeadlineTest() {
        try {
            Deadline temp = new Deadline("do this task", "2012-01-21");
            assertEquals("[D][ ] do this task (by: Jan 21 2012)", temp.toString());
        } catch (DukeException e) {
            //This should not happen from the above input
        }
    }
}
