package ava.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void markTest() {
        byte[] emojiCheckByteCode = new byte[]{(byte) 0xE2, (byte) 0x9C, (byte) 0x85};
        String check = new String(emojiCheckByteCode, Charset.forName("UTF-8"));
        Deadline deadline = new Deadline("cs2103 ip", false,
                LocalDateTime.parse("2022-08-26 16:00", timeFormat));
        assertEquals(deadline.markDone().toString(), check + " - cs2103 ip is due by 26-Aug-2022 16:00");
    }

    @Test
    public void formatFileTest() {
        Deadline deadline = new Deadline("cs2100 quiz", false,
                LocalDateTime.parse("2022-08-23 15:30", timeFormat));
        assertEquals(deadline.formatChange(), "D | 0 | cs2100 quiz | 2022-08-23 15:30");
    }
}
