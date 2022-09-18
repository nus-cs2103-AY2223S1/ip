package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    @DisplayName("Check String Output of Todo Object")
    public void checkStringOutputOfToDoObject() {
        String output = new ToDos("read book", false).toString();
        assertEquals("[T][ ] read book", output);
    }

    @Test
    @DisplayName("Check Save String Output of Todo Object")
    public void checkSaveStringOutputOfToDoObject() {
        String output = new ToDos("read book", false).toSaveString();
        assertEquals("todo 0 read book", output);
    }

}
