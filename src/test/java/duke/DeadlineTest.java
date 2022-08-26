package duke;

import duke.exceptions.ImproperFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    void testToString() {

        try {
            Deadline unmarkedTest = new Deadline("unmarkedDeadline", " 2022-10-10 18:00");
            Deadline markedTest = new Deadline("markedDeadline", " 2022-10-10 18:00");
            markedTest.toggleStatus();
            String unmarkedTestExpected = "[D] [ ] unmarkedDeadline (by: Oct 10 2022, 6:00 PM)";
            String markedTestExpected = "[D] [X] markedDeadline (by: Oct 10 2022, 6:00 PM)";
            assertEquals(unmarkedTestExpected, unmarkedTest.toString(), "toString an unmarked deadline.");
            assertEquals(markedTestExpected, markedTest.toString(), "toString a marked deadline.");
        } catch (ImproperFormatException e) {
            System.out.println(e);
        }
    }

    @Test
    void testToSaveVersion() {
        try {
            Deadline unmarkedTest = new Deadline("unmarkedDeadline", " 2022-10-10 18:00");
            Deadline markedTest = new Deadline("markedDeadline", " 2022-10-10 18:00");
            markedTest.toggleStatus();
            String unmarkedTestExpected = "D|0|unmarkedDeadline| 2022-10-10 18:00\n";
            String markedTestExpected = "D|1|markedDeadline| 2022-10-10 18:00\n";
            assertEquals(unmarkedTestExpected, unmarkedTest.toSaveVersion(), "toSaveVersion an unmarked deadline.");
            assertEquals(markedTestExpected, markedTest.toSaveVersion(), "toSaveVersion a marked deadline.");
        } catch (ImproperFormatException e) {
            System.out.println(e);
        }
    }
}
