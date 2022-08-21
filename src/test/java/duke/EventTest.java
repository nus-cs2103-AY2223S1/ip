package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testFileRepresentation() {
        assertEquals("E | 0 | test description | 2022-08-21",
                     new Event("test description",
                               LocalDate.parse("2022-08-21")).toFileRepresentation());
    }

}
