import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mia.Deadline;

public class DeadlineTest {
    private static final String DUMMY_DEADLINE = "23 January 2022 08:00;;0;;Title;;";

    @Test
    public void saveFormatTest() {
        final Deadline deadline = Deadline.fromSaveFormat(DUMMY_DEADLINE);
        assertEquals("D;;" + DUMMY_DEADLINE, deadline.toSaveFormat());
    }

    @Test
    public void stringTest() {
        final Deadline deadline = Deadline.fromSaveFormat(DUMMY_DEADLINE);
        assertEquals("‼ ☐ Title (by 2022-01-23 at 08:00)", deadline.toString());
    }
}
