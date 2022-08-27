package ploopy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testMarkDone() {
        ToDo todo = new ToDo("finish 2103T work");
        todo.markDone();
        assertEquals("[T][✓] finish 2103T work", todo.toString());
    }
}
