package dobby;

import dobby.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void toString_newTask_taskString() {
        assertEquals(new Todo("testDescription").toString(), "[T][ ] testDescription");
    }

    @Test
    public void toPrint_newTask_taskStringForFile() {
        assertEquals(new Todo("testDescription").toPrint(), "T | [ ] | testDescription");
    }
}
