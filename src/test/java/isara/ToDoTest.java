package isara;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import isara.task.Task;
import isara.task.ToDo;

public class ToDoTest {
    @Test
    public void testMarkFunctionalityForToDo() {
        Task toDo = new ToDo("Run in the Morning");
        assertEquals(toDo.toString(), "[T][ ] Run in the Morning");
        toDo.mark();
        assertEquals(toDo.toString(), "[T][X] Run in the Morning");
    }

    @Test
    public void testUnmarkFunctionalityForToDo() {
        Task toDo = new ToDo("Run in the Morning");
        toDo.mark();
        assertEquals(toDo.toString(), "[T][X] Run in the Morning");
        toDo.unmark();
        assertEquals(toDo.toString(), "[T][ ] Run in the Morning");
    }

    @Test
    public void testGetStatusIconForToDo() {
        Task toDo = new ToDo("Run in the Morning");
        toDo.mark();
        assertEquals(toDo.toString(), "[T][X] Run in the Morning");
        assertEquals(toDo.getStatusIcon(), "X");
        toDo.unmark();
        assertEquals(toDo.toString(), "[T][ ] Run in the Morning");
        assertEquals(toDo.getStatusIcon(), " ");
    }
}
