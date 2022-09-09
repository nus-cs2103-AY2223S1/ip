package cs2103t.ip.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void addTodoTest() {
        Todo todo = new Todo("Read Textbook");
        assertEquals(todo.addString(2), "_______________________________\n" +
                "Got it. I've added this task: \n" +
                "[T][ ] Read Textbook\n" +
                "Now you have 2 tasks in the list \n" +
                "_______________________________\n");
    }
}
