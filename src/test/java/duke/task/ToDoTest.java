package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void newToDoTest(){
        ToDo td = new ToDo("example");
        assertEquals("[T][ ] example", td.toString());
    }
}
