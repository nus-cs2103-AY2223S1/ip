package duke;

import duke.exceptions.ImproperFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    void testToString() {
        try {
            Event unmarkedTest = new Event("unmarkedEvent", " 2022-10-10 18:00");
            Event markedTest = new Event("markedEvent", " 2022-10-10 18:00");
            markedTest.toggleStatus();
            String unmarkedTestExpected = "[E] [ ] unmarkedEvent (at: Oct 10 2022, 6:00 PM)";
            String markedTestExpected = "[E] [X] markedEvent (at: Oct 10 2022, 6:00 PM)";
            assertEquals(unmarkedTestExpected, unmarkedTest.toString(), "toString an unmarked event.");
            assertEquals(markedTestExpected, markedTest.toString(), "toString a marked event.");
        } catch (ImproperFormatException e) {
            System.out.println(e);
        }
    }

    @Test
    void testToSaveVersion() {
        try {
            Event unmarkedTest = new Event("unmarkedEvent", " 2022-10-10 18:00");
            Event markedTest = new Event("markedEvent", " 2022-10-10 18:00");
            markedTest.toggleStatus();
            String unmarkedTestExpected = "E|0|unmarkedEvent| 2022-10-10 18:00\n";
            String markedTestExpected = "E|1|markedEvent| 2022-10-10 18:00\n";
            assertEquals(unmarkedTestExpected, unmarkedTest.toSaveVersion(), "toSaveVersion an unmarked event.");
            assertEquals(markedTestExpected, markedTest.toSaveVersion(), "toSaveVersion a marked event.");
        } catch (ImproperFormatException e) {
            System.out.println(e);
        }
    }
}
