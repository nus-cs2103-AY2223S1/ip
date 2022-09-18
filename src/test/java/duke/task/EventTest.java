package duke.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EventTest {

    private static Event event;

    @BeforeAll
    public static void setUpEvent() {
        event = new Event("test description",
                LocalDate.parse("2022-08-21"));
    }

    @Test
    public void isOn_sameDate_returnTrue() {
        assertTrue(event.isOn(LocalDate.parse("2022-08-21")));
    }

    @Test
    public void isOn_differentDate_returnFalse() {
        assertFalse(event.isOn(LocalDate.parse("2022-08-22")));
    }

    @Test
    public void toFileRepresentation_convert_success() {
        assertEquals("E | 0 | test description | 2022-08-21",
                     event.toFileRepresentation());
    }

    @Test
    public void fromFileRepresentation_convert_success() {
        Event result = assertDoesNotThrow(() -> Event.fromFileRepresentation("E | 0 | test description | 2022-08-21"));
        assertEquals(event, result);
    }
}
