package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void encodeTest(){
        ToDo todo = new ToDo("homework");
        assertEquals("T" + Task.ENCODING_SEPARATOR + "homework" + Task.ENCODING_SEPARATOR + "false",
                todo.encode());
        todo = new ToDo("homework", true);
        assertEquals("T" + Task.ENCODING_SEPARATOR + "homework" + Task.ENCODING_SEPARATOR + "true",
                todo.encode());
    }

    @Test
    public void toStringTest(){
        ToDo todo = new ToDo("homework");
        assertEquals("[T][ ] homework",
                todo.toString());
        todo = new ToDo("homework", true);
        assertEquals("[T][X] homework",
                todo.toString());
    }
}
