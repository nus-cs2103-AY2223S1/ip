package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {
    @Test
    public void testToString() {
        assertEquals("[D][ ] ps1 (by: 23:59 Sep 3 2022)",
                new Deadline("ps1", false, LocalDateTime.parse("2022-09-03T23:59")).toString());
    }

    @Test
    public void testGetDescription() {
        assertEquals("D | F | ps1 | 2022-09-03T23:59\n",
                new Deadline("ps1", false, LocalDateTime.parse("2022-09-03T23:59")).getDescription());
    }
}

