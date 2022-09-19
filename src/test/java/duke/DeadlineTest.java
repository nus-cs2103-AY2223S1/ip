package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void deadlineTest() throws DukeException {
        assertEquals("Sep 16 2022", new Deadline("Deadline", "2022-09-16").deadline
                .format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Test
    public void toStringTest() throws DukeException {
        assertEquals("[D][ ] Deadline (by: Dec 16 2022)",
                new Deadline("Deadline", "2022-12-16").toString());
    }

    @Test
    public void toDataTest() throws DukeException {
        assertEquals("D,  , Deadline, 2022-09-16", new Deadline("Deadline", "2022-09-16").toData());
    }
}
