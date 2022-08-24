package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void createToDoTest() {
        ToDo todo  = new ToDo("Test");
        assertEquals("[T][ ] Test",todo.toString());
    }

    @Test
    public void createToDoTest2() {
        ToDo todo  = new ToDo("Test", false);
        assertEquals("[T][ ] Test",todo.toString());
    }

    @Test
    public void createToDoTest3() {
        ToDo todo  = new ToDo("Test", true);
        assertEquals("[T][X] Test",todo.toString());
    }

    @Test
    public void saveFileStringTest() {
        ToDo todo  = new ToDo("Test", false);
        assertEquals("[T] @ [ ] @ Test",todo.toSaveFileString());
    }

    @Test
    public void saveFileStringTest2() {
        ToDo todo  = new ToDo("Test", true);
        assertEquals("[T] @ [X] @ Test",todo.toSaveFileString());
    }

    @Test
    public void markAsDoneTest() {
        ToDo todo  = new ToDo("Test", false);
        todo.markAsDone();
        assertEquals("[T][X] Test",todo.toString());
    }

    @Test
    public void markAsUndoneTest() {
        ToDo todo  = new ToDo("Test", true);
        todo.markAsUndone();
        assertEquals("[T][ ] Test",todo.toString());
    }
}
