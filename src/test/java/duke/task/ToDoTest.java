package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void getDescriptionTest () {
        ToDo t = new ToDo("Test2");
        assertEquals("[T]" + "[ ] " + "Test2", t.getDescription());
    }
}
