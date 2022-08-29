package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class TaskTest {
    @Test
    public void getStatusIconTest() {
        Task markedTodo =  new Todo("Finish iP deliverables up to week 4");
        markedTodo.markAsDone();
        Task unmarkedTodo = new Todo("Buy groceries from the supermarket");
        assertEquals("X", markedTodo.getStatusIcon());
        assertEquals(" ", unmarkedTodo.getStatusIcon());
    }

    @Test
    public void toStringTest() {
        Task event = new Event("Go to Sentosa to relax", "2019-12-18");
        assertEquals("[E][ ] Go to Sentosa to relax (at: Dec 18 2019)", event.toString());
    }
}
