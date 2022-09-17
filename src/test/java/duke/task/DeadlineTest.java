package duke.task;
import duke.exceptions.InvalidDeadlineException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void constructor_invalidDate_exceptionThrown() {
        assertThrows(InvalidDeadlineException.class,
                     () -> new Deadline("Test task", "09-17-2022", false));

        assertThrows(InvalidDeadlineException.class,
                () -> new Deadline("Test task", "blah blah", false));
    }

    @Test
    public void constructor_validDate_exceptionNotThrown(){
        assertDoesNotThrow(() -> new Deadline("Test task", "2022-09-17", false));
        assertDoesNotThrow(() -> new Deadline("Test task", " 2022-09-17  ", false));
    }

    @Test
    public void constructor_validDate_deadlineIsCorrect(){
        assertDoesNotThrow(() -> {
            Deadline deadline = new Deadline("Test task", "2022-09-17", false);
            assertEquals(deadline.by, LocalDate.of(2022, 9, 17));
        });
    }

    @Test
    public void constructor_validDateWithWhitespaces_deadlineIsCorrect(){
        assertDoesNotThrow(() -> {
            Deadline deadline = new Deadline("Test task", "  2022-09-17  \n ", false);
            assertEquals(deadline.by, LocalDate.of(2022, 9, 17));
        });
    }
}
