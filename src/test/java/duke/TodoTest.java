package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    Todo test = new Todo("Homework");

    @Test
    public void toStringTest() {
        assertEquals(test.toString(), "[T][ ] Homework");
    }

    @Test
    public void toSaveTest() {
        assertEquals(test.toSaveData(), "T | 0 | Homework");
    }
}
