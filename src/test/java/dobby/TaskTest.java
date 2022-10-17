package dobby;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dobby.tasks.Todo;


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
