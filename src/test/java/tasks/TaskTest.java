package tasks;

import org.junit.jupiter.api.Test;
import util.ParsedData;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskTest {
    @Test
    public void taskCreation() {
        Task event1 = new Event(new ParsedData("hello", "by", "tonight"));
        Task todo1 = new Todo(new ParsedData("bye"));
        assertEquals("tonight",
                event1.getTimeText());
        assertEquals("bye",
                todo1.getDescription());
    }
}
