package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testToDoToString() {
        assertEquals("[T] [ ] testing", new ToDo("testing").toString());
    }

    @Test
    public void testSaveStringFormat() {
        assertEquals("T | 0 | testing", new ToDo("testing").saveStringFormat());
    }

    @Test
    public void markTodo_success() {
        ToDo toDo = new ToDo("Play");
        toDo.markDone();
        assertEquals("[T] [X] Play", toDo.toString());
    }

    @Test
    public void unmarkEvent_success() {
        ToDo toDo = new ToDo("Play");
        toDo.markUndone();
        assertEquals("[T] [ ] Play", toDo.toString());
    }


}
