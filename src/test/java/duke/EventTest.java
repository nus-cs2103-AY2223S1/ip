package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.task.Event;

public class EventTest {
    @Test
    public void testFileRepresentation() {
        assertEquals("E | 0 | test description | 2022-08-21",
                     new Event("test description",
                               LocalDate.parse("2022-08-21")).toFileRepresentation());
    }

}
