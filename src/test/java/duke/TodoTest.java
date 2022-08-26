package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void toString_newTodo_correctString(){
        String actualString = new Todo("Hello").toString();
        String expectedString = "[T] [ ] Hello";

        assertEquals(expectedString, actualString);
    }

    @Test
    public void toSavedString_newTodo_correctString(){
        Todo todo = new Todo("Hello there");

        String actualString = todo.toSavedString();
        String expectedString = "T#0#Hello there";

        assertEquals(expectedString, actualString);
    }

    @Test
    public void toSavedString_newTodoWithMark_correctString(){
        Todo todo = new Todo("Hello there");
        todo.markDone();

        String actualString = todo.toSavedString();
        String expectedString = "T#1#Hello there";

        assertEquals(expectedString, actualString);
    }
}
