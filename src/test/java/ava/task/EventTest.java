package ava.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {
    private static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void markTest() {
        byte[] emojiCheckByteCode = new byte[]{(byte) 0xE2, (byte) 0x9C, (byte) 0x85};
        String check = new String(emojiCheckByteCode, Charset.forName("UTF-8"));
        Event event = new Event("NUSSU EXCO Election", false,
                LocalDateTime.parse("2022-08-24 00:00", timeFormat));
        assertEquals(event.markDone().toString(), check + " - NUSSU EXCO Election at 24-Aug-2022 00:00");
    }

    @Test
    public void formatFileTest() {
        Event event = new Event("NUS Jazz Band Practice", false,
                LocalDateTime.parse("2022-09-01 20:30", timeFormat));
        assertEquals(event.formatChange(), "E | 0 | NUS Jazz Band Practice | 2022-09-01 20:30");
    }
}
