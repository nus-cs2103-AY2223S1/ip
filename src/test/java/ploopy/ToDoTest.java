package ploopy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {

    @Test
    public void testMarkDone() {
        ToDo todo = new ToDo("finish 2103T work");
        todo.markDone();
        assertEquals("[T][✓] finish 2103T work", todo.toString());
    }
}
