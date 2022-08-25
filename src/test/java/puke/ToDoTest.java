package puke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void addToDoTest() {
        Task t = new ToDo("Bring textbook");
        assertEquals("[T][ ] Bring textbook", t.toString());
    }
}
