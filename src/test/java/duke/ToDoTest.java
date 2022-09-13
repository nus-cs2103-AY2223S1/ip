package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void toStringTest() {
        assertEquals("[T][ ]  read book", new ToDo(" read book").toString());
    }

    @Test
    public void saveFileStringTest() {
        assertEquals("T | 0 |  read book", new ToDo(" read book").saveString());
    }

}
