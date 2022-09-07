package duke;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void toDo_toString_testDescription() {
        assertEquals("[D][ ] borrow book (by: Oct-10-2022)",
                new Deadline("borrow book", LocalDate.parse("2022-10-10")).toString());
    }

    @Test
    public void toDo_getSaveString_testDescription() {
        assertEquals("D | 0 | borrow book | 2022-10-10",
                new Deadline("borrow book", LocalDate.parse("2022-10-10")).getSaveString());
    }
}
