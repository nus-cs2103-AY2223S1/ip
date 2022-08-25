package duke.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void toStorage_success() {
        ToDo toDo = new ToDo("go fishing");
        assertEquals(toDo.toStorage(), "T | 0 | go fishing\n");
    }
}