package candice.task;

import candice.exception.InvalidMarkException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testToDoStatusString() throws InvalidMarkException {
        Task.ToDo toDoTask = new Task.ToDo("read a book");
        assertEquals("[T][ ] read a book", toDoTask.getStatus());
        toDoTask.setFinished(); // should never throw InvalidMarkException
        assertEquals("[T][X] read a book", toDoTask.getStatus());
    }

    @Test
    public void testToDoStorageDescription() throws InvalidMarkException {
        Task.ToDo toDoTask = new Task.ToDo("watch cs2103 lecture");
        assertEquals("[T], unfinished, watch cs2103 lecture\n", toDoTask.getStorageDescription());
        toDoTask.setFinished(); // should never throw InvalidMarkException
        assertEquals("[T], finished, watch cs2103 lecture\n", toDoTask.getStorageDescription());
    }
}
