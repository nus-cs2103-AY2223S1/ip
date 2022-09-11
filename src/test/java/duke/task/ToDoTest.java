package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ToDoTest {

    @Test
    public void getDescriptionTest() {
        ToDo t = new ToDo("Test2", 'X');
        assertEquals("[T]" + "[X] " + "Test2", t.getDescription());
    }
}
