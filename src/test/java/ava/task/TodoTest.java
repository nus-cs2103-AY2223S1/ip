package ava.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void markTest() {
        byte[] emojiCheckByteCode = new byte[]{(byte) 0xE2, (byte) 0x9C, (byte) 0x85};
        String check = new String(emojiCheckByteCode, Charset.forName("UTF-8"));
        Todo todo = new Todo("Eat lunch", false);
        assertEquals(todo.markDone().toString(), check + " - Eat lunch");
    }

    @Test
    public void formatFileTest() {
        Todo todo = new Todo("Read Pre-Lecture Notes", false);
        assertEquals(todo.formatChange(), "T | 0 | Read Pre-Lecture Notes");
    }
}
