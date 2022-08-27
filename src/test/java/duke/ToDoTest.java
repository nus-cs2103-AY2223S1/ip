package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void testToString() {
        ToDo newToDo = new ToDo("Do laundry");
        String newToDoToString = "[T][ ] Do laundry";
        assertEquals(newToDo.toString(), newToDoToString);
    }
}