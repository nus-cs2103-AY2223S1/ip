package duke;

import duke.task.Deadline;

import org.junit.jupiter.api.Test;
    
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        try {
            Deadline deadline = new Deadline("deadline", "25/08/2022 06:00");
            assertEquals("[D][ ] deadline (by: 25/08/2022 06:00)", deadline.toString());
        } catch (DukeException e) {
            System.out.println(e);
        }
        DukeException thrown = assertThrows(DukeException.class, () -> new Deadline("deadline", "wrong time format"));
        assertTrue(thrown.getMessage().contains("Wrong datetime format"));
    }

    @Test
    public void getSaveFormatTest() {
        try {
            Deadline deadline = new Deadline("deadline", "25/08/2022 06:00");
            assertEquals("D | 0 | deadline | 25/08/2022 06:00", deadline.getSaveFormat());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}

