package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.utilities.DukeException;

public class EventTest {
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private Event event;

    {
        try {
            event = new Event(
                    "This is a dummy event task.",
                    LocalDateTime.parse("2022-09-21 19:00", dateTimeFormatter),
                    LocalDateTime.parse("2022-09-21 20:00", dateTimeFormatter)
            );
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void event_emptyDescription_exceptionThrown() {
        DukeException de = assertThrows(DukeException.class, () -> {
            new Event(
                    "",
                    LocalDateTime.parse("2022-09-21 19:00", dateTimeFormatter),
                    LocalDateTime.parse("2022-09-21 20:00", dateTimeFormatter)
            );
        });

        assertEquals("The description of event task is missing information!", de.getMessage());
    }

    @Test
    public void getStart_stringRepresentation_correct() {
        assertEquals("2022-09-21 19:00", event.getStart());
    }

    @Test
    public void getEnd_stringRepresentation_correct() {
        assertEquals("2022-09-21 20:00", event.getEnd());
    }

    @Test
    public void toString_stringRepresentation_correct() {
        assertEquals(
                "[E][ ] This is a dummy event task. (at: Sep 21 2022 19:00 to Sep 21 2022 20:00)",
                event.toString()
        );
    }
}
