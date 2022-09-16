import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.ToDo;

public class ToDoTest {
    @Test
    public void toDoTest() {
        assertEquals(new ToDo("Read book").toString(), "[T][ ] Read book");
    }

    @Test
    public void markToDoTest() {
        ToDo toDo = new ToDo("Read book");
        toDo.markAsDone();
        assertEquals(toDo.toString(), "[T][X] Read book");
    }

    @Test
    public void unmarkToDoTest() {
        ToDo toDo = new ToDo("Read book");
        toDo.markAsDone();
        toDo.markAsUndone();
        assertEquals(toDo.toString(), "[T][ ] Read book");
    }
}
