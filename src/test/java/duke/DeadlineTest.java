package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testToString() {
        LocalDateTime ldt = LocalDateTime.of(2022, 8, 26, 16, 00);
        Deadline d = new Deadline("submit CS2103 Week 3 quiz!", ldt);
        assertEquals("[D][ ] submit CS2103 Week 3 quiz! (by: 26-Aug-2022 16:00)", d.toString());
    }

    @Test
    public void testGetDate() {
        LocalDateTime ldt = LocalDateTime.of(2022, 8, 26, 16, 00);
        Deadline d = new Deadline("submit CS2103 Week 3 quiz!", ldt);
        assertEquals("26-Aug-2022", d.getDate());
    }

    @Test
    public void testGetTime() {
        LocalDateTime ldt = LocalDateTime.of(2022, 8, 26, 16, 00);
        Deadline d = new Deadline("submit CS2103 Week 3 quiz!", ldt);
        assertEquals("16:00", d.getTime());
    }
}
