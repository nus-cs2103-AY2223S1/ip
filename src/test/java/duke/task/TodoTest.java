package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void inputTest1() {
        String expected = "T | 0 | read book\n";
        String actual = new Todo("read book").formatTask();
        assertEquals(expected, actual);
    }

    @Test
    public void inputTest2() {
        String expected = "T | 0 | return book\n";
        String actual = new Todo("return book").formatTask();
        assertEquals(expected, actual);
    }

    @Test
    public void stringOutputTest() {
        String expected = "[T][ ] read book";
        String actual = new Todo("read book").toString();
        assertEquals(expected, actual);
    }
}