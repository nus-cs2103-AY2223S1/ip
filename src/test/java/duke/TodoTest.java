package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    private Todo test = new Todo("Homework");

    @Test
    public void toStringTest() {
        assertEquals(test.toString(), "[T][ ] Homework");
    }

    @Test
    public void toSaveTest() {
        assertEquals(test.toSaveData(), "T | 0 | Homework");
    }
}
