package ava;

import ava.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    public static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void markTest() {
        Deadline deadline = new Deadline("cs2103 ip", false,
                LocalDateTime.parse("2022-08-26 16:00", timeFormat));
        assertEquals(deadline.markDone().toString(), "[D][X] cs2103 ip (by: 26-Aug-2022 16:00)");
    }

    @Test
    public void formatFileTest() {
        Deadline deadline = new Deadline("cs2100 quiz", false,
                LocalDateTime.parse("2022-08-23 15:30", timeFormat));
        assertEquals(deadline.formatChange(), "D | 0 | cs2100 quiz | 2022-08-23 15:30");
    }
}
