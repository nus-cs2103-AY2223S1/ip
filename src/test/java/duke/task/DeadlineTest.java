package duke.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DeadlineTest {
    private static Deadline deadline;

    @BeforeAll
    public static void setUpEvent() {
        deadline = new Deadline("test description",
                LocalDate.parse("2022-08-21"));
    }

    @Test
    public void isOn_sameDate_returnTrue() {
        assertTrue(deadline.isOn(LocalDate.parse("2022-08-21")));
    }

    @Test
    public void isOn_differentDate_returnFalse() {
        assertFalse(deadline.isOn(LocalDate.parse("2022-08-22")));
    }

    @Test
    public void toFileRepresentation_convert_success() {
        assertEquals("D | 0 | test description | 2022-08-21",
                deadline.toFileRepresentation());
    }

    @Test
    public void fromFileRepresentation_convert_success() {
        Deadline result = assertDoesNotThrow(() ->
                Deadline.fromFileRepresentation("D | 0 | test description | 2022-08-21"));
        assertEquals(deadline, result);
    }
}
