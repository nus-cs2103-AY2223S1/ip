package isara;

import isara.task.Task;
import isara.task.ToDo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testMarkFunctionalityForToDo(){
        Task toDo = new ToDo("Run in the Morning");
        assertEquals(toDo.toString(), "[T][ ] Run in the Morning");
        toDo.markAsDone();
        assertEquals(toDo.toString(), "[T][X] Run in the Morning");
    }

    @Test
    public void testUnmarkFunctionalityForToDo(){
        Task toDo = new ToDo("Run in the Morning");
        toDo.markAsDone();
        assertEquals(toDo.toString(), "[T][X] Run in the Morning");
        toDo.unmarkAsDone();
        assertEquals(toDo.toString(), "[T][ ] Run in the Morning");
    }

    @Test
    public void testGetStatusIconForToDo(){
        Task toDo = new ToDo("Run in the Morning");
        toDo.markAsDone();
        assertEquals(toDo.toString(), "[T][X] Run in the Morning");
        assertEquals(toDo.getStatusIcon(), "X");
        toDo.unmarkAsDone();
        assertEquals(toDo.toString(), "[T][ ] Run in the Morning");
        assertEquals(toDo.getStatusIcon(), " ");
    }
}
