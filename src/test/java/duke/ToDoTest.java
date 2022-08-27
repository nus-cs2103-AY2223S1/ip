package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void toStringTest() {
        String description = "1.[T][ ] return book";
        ToDo newToDo = new ToDo(false, "return book", 1);
        assertEquals(description, newToDo.toString());
    }
}
