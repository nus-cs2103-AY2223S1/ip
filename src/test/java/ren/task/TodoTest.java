package ren.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toString_description_success() {
        assertEquals("[T][ ] test\n", new Todo("test").toString());
    }

    @Test
    public void writeData_description_success() {
        assertEquals("T| |test", new Todo("test").writeData());
    }

    @Test
    public void readData_description_success() {
        Todo test = Todo.readData(new String[] {"T", " ", "test"});
        assert test != null;
        assertEquals("T| |test", test.writeData());
    }
}
