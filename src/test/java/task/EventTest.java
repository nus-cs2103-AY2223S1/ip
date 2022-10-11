package task;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventTest {

    private boolean isAssertOn() {
        boolean ea = false;
        try {
            assert(false);
        } catch (AssertionError e) {
            ea = true;
        }
        return ea;
    }

    @Test
    public void event_validDescriptionOrDate_createsEvent() {
        Assertions.assertTrue(new Event("description", LocalDate.now()) instanceof Event);

        // check is assertion have been enabled ...
        // credit https://stackoverflow.com/questions/13029915/how-to-programmatically-test-if-assertions-are-enabled
        // author: Christopher Mindus

        if (isAssertOn()) {
            Assertions.assertThrows(AssertionError.class, () -> new Event(null, null));
        }
    }

    @Test
    public void toString_emptyFields_throwsDukeException() {
        if (isAssertOn()) {
            Assertions.assertThrows(AssertionError.class, () -> new Event(null, null).toString());
        } else {
            assert(true);
        }
    }

    @Test
    public void isToday_eventIsNotToday_returnsFalse() {
        Event e = new Event("event", LocalDate.of(1000, 04, 04));
        Assertions.assertFalse(e.isToday());
    }
}
