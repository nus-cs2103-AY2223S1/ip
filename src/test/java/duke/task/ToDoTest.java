package duke.task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ToDoTest {
    @Test
    public void todoTest1(){
        ToDo todo = new ToDo("eat ramen");
        assertEquals("[T][ ] eat ramen", todo.toString());
    }

    @Test
    public void todoTest2(){
        ToDo todo = new ToDo("test wires");
        assertEquals("[T][ ] test wires", todo.toString());
    }

    @Test
    public void todoTest3(){
        ToDo todo = new ToDo("test wires");
        assertFalse(todo.isOnThisDate("2022-09-08"));
    }
}
