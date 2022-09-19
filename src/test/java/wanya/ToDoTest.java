package wanya;

import wanya.task.ToDo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void todoToStringTest() {
        ToDo todo = new ToDo ("Homework", true);
        assertEquals("[T][X] Homework", todo .toString());

        ToDo todo2 = new ToDo ("Homework2", false);
        assertEquals("[T][ ] Homework2", todo2.toString());
    }

    @Test
    public void todoToStorageTest() {
        ToDo todo = new ToDo ("Homework", false);
        assertEquals("T|0|Homework", todo.toStorageString());

        ToDo todo2 = new ToDo ("Homework2", true);
        assertEquals("T|1|Homework2", todo2.toStorageString());
    }
}
