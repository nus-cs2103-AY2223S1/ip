package duke;

import duke.tasks.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void testToString() {
        Deadline d = new Deadline("return book", LocalDate.parse("2020-11-30"));
        assertEquals("[D][ ] return book (by: Nov 30 2020)", d.toString());
    }

    @Test
    void taskMemo() {
        Deadline d = new Deadline("return book", LocalDate.parse("2020-11-30"));
        assertEquals("D | 0 | return book | 2020-11-30", d.taskMemo());
    }
}
