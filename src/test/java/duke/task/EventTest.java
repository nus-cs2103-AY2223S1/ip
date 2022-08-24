package duke.task;
import duke.exceptions.InvalidEventException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void constructor_invalidDate_exceptionThrown() {
        assertThrows(InvalidEventException.class,
                () -> new Event("Test task", "09-17-2022", false));

        assertThrows(InvalidEventException.class,
                () -> new Event("Test task", "blah blah", false));
    }

    @Test
    public void constructor_validDate_exceptionNotThrown(){
        assertDoesNotThrow(() -> new Event("Test task", "2022-09-17", false));
        assertDoesNotThrow(() -> new Event("Test task", " 2022-09-17  ", false));
    }

    @Test
    public void constructor_validDate_deadlineIsCorrect(){
        assertDoesNotThrow(() -> {
            Event event = new Event("Test task", "2022-09-17", false);
            assertEquals(event.at, LocalDate.of(2022, 9, 17));
        });
    }

    @Test
    public void constructor_validDateWithWhitespaces_deadlineIsCorrect(){
        assertDoesNotThrow(() -> {
            Event event = new Event("Test task", "  2022-09-17  \n ", false);
            assertEquals(event.at, LocalDate.of(2022, 9, 17));
        });
    }
}
