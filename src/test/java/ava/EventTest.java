package ava;

import ava.task.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    public static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void markTest() {
        Event event = new Event("NUSSU EXCO Election", false,
                LocalDateTime.parse("2022-08-24 00:00", timeFormat));
        assertEquals(event.markDone().toString(), "[E][X] NUSSU EXCO Election (at: 24-Aug-2022 00:00)");
    }

    @Test
    public void formatFileTest() {
        Event event = new Event("NUS Jazz Band Practice", false,
                LocalDateTime.parse("2022-09-01 20:30", timeFormat));
        assertEquals(event.formatChange(), "E | 0 | NUS Jazz Band Practice | 2022-09-01 20:30");
    }
}
