package candice.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testToDoStatusString() {
        Task.ToDo toDoTask = new Task.ToDo("read a book");
        assertEquals("[T][ ] read a book", toDoTask.getStatus());
        toDoTask.setFinished();
        assertEquals("[T][X] read a book", toDoTask.getStatus());
    }

    @Test
    public void testToDoStorageDescription() {
        Task.ToDo toDoTask = new Task.ToDo("watch cs2103 lecture");
        assertEquals("[T], unfinished, watch cs2103 lecture\n", toDoTask.getStorageDescription());
        toDoTask.setFinished();
        assertEquals("[T], finished, watch cs2103 lecture\n", toDoTask.getStorageDescription());
    }
}
