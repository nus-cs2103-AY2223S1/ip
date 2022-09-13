package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.utilities.DukeException;

public class DeadlineTest {
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private Deadline deadline;

    {
        try {
            deadline = new Deadline(
                    "This is a dummy deadline task.",
                    LocalDateTime.parse("2022-09-21 19:00", dateTimeFormatter)
            );
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deadline_emptyDescription_exceptionThrown() {
        DukeException de = assertThrows(DukeException.class, () -> {
            new Deadline("", LocalDateTime.parse("2022-09-21 19:00", dateTimeFormatter));
        });

        assertEquals("The description of deadline task is missing information!", de.getMessage());
    }

    @Test
    public void getBy_stringRepresentation_correct() {
        assertEquals("2022-09-21 19:00", deadline.getBy());
    }

    @Test
    public void toString_stringRepresentation_correct() {
        assertEquals("[D][ ] This is a dummy deadline task. (by: Sep 21 2022 19:00)", deadline.toString());
    }
}
