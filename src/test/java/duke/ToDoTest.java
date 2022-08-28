package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testReturnDescription() {
        ToDo todo = new ToDo("read book");

        //Test Case 1
        assertEquals("[T][ ] read book", todo.returnDescription());
    }

    @Test
    public void testToWriteFile() {
        ToDo todo = new ToDo("read book");
        ToDo todoTwo = new ToDo("read big book", true);

        //Test Case 1
        assertEquals("T , false , read book", todo.toWriteFile());

        //Test Case 2
        assertEquals("T , true , read big book", todoTwo.toWriteFile());
    }
}
