package DukeBot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void taskTypeTest() {
        assertEquals("T", new ToDo("").getTaskType());
    }

    @Test
    public void stringConversionTest() {
        assertEquals("[T][ ] go to fair", new ToDo("go to fair").toString());
    }
}
