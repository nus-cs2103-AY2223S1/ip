package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void getStringToSaveTest() {
        assertEquals("T | 0 | return book",
                new Todo("return book").getStringToSave());
    }

    @Test
    public void toStringTest() {
        assertEquals("[T][ ] return book",
                new Todo("return book").toString());
    }
}
