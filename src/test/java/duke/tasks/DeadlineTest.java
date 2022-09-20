package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.exceptions.DukeException;

public class DeadlineTest {

    @Test
    public void toStringTest() {
        try {
            assertEquals("[D][ ] Test message (by: Feb 2 2022 5.00p.m.)", (new Deadline("Test message", "2022-02-02 1700")).toString());
            Deadline deadline2 = new Deadline("Test marked message", "2022-02-02 1700");
            deadline2.markAsDone();
            assertEquals("[D][X] Test marked message (by: Feb 2 2022 5.00p.m.)", deadline2.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void toStringForFileTest() {
        assertEquals("N|todo test message", (new Todo("test message")).toStringForFile());
    }
}
