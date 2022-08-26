package duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void descriptionTest() throws DukeException {
        String expectedString = "[T][] return book";
        Todo expectedTodo = new Todo("return book");

        assertEquals(expectedTodo.toString(), expectedString);
    }

    @Test
    public void anotherDescriptionTest() throws DukeException {
        String expectedString = "[T][] do homework";
        Todo expectedTodo = new Todo("do homework");

        assertEquals(expectedTodo.toString(), expectedString);
    }
}
