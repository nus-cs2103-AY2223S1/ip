package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



public class ToDoTest {
    @Test
    public void addToDoTest() {
        ToDo toDo = new ToDo("todo asking");
        assertEquals("[T][ ] asking", toDo.toString());
    }

    @Test
    public void markToDoTest() {
        ToDo toDo = new ToDo("todo asking");
        toDo.markAsDone();
        assertEquals("[T][X] asking", toDo.toString());
    }
}
