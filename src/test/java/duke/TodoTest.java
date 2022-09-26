package duke;

import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void instantiate_success() {
        Todo task = new Todo("");
        assertEquals("[T][ ][Low] ", task.toString());
    }

    @Test
    public void toString_success() {
        Todo task = new Todo("Feed cat");
        assertEquals("[T][ ][Low] Feed cat", task.toString());
    }

    @Test
    public void toFileString_success() {
        Todo task = new Todo("Feed cat");
        assertEquals("T|0|3|Feed cat", task.toFileString());
    }

    @Test
    public void setTaskStatus_success() {
        Todo task = new Todo("Feed cat");
        assertEquals("[T][ ][Low] Feed cat", task.toString());
        task.setTaskStatus(true);
        assertEquals("[T][X][Low] Feed cat", task.toString());
    }
}
