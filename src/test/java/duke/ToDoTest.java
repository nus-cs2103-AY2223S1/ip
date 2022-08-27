package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void toStringTest() {
        ToDo todo = new ToDo("Finish iP");
        assertEquals(todo.toString(), "[T][ ] Finish iP");
    }

    @Test
    public void setMarkedTest() {
        ToDo todo = new ToDo("Setup Gradle");
        assertEquals(todo.toString(), "[T][ ] Setup Gradle");
        todo.setMarked(true);
        assertEquals(todo.toString(), "[T][X] Setup Gradle");
    }
}
