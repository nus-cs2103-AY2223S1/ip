import duke.tasks.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void testToDo() {
        Todo newToDo = new Todo("To Do Test");
        assertEquals(newToDo.output(), "[T][ ] To Do Test");
    }

    @Test
    public void testDeadline() {
        Deadline newDeadline = new Deadline("Deadline Test", "2044-04-04");
        assertEquals(newDeadline.output(), "[D][ ] Deadline Test (by: 04 April 2044)");
    }

}
