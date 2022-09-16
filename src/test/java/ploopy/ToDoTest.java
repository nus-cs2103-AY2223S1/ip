package ploopy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ploopy.task.ToDo;

public class ToDoTest {

    @Test
    public void testMarkDone() {
        ToDo todo = new ToDo("finish 2103T work");
        todo.markDone();
        assertEquals("[T][X] finish 2103T work", todo.toString());
    }
}
