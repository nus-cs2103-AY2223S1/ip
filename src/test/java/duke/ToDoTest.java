package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class ToDoTest {

    @Test
    void testToString() {
        ToDo newToDo = new ToDo("Do laundry");
        String newToDoToString = "[T][ ] Do laundry";
        assertEquals(newToDo.toString(), newToDoToString);
    }
}