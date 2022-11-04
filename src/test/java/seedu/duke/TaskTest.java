package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.Task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

public class TaskTest {

    @Test
    public void Test1(){
        Task t = new Task("Dinner Party", "event", LocalDate.parse("2013-12-23"));
        assertEquals(t.toString(), "[E][ ] Dinner Party(at Dec 23 2013)");
    }

    @Test
    public void Test2(){
        Task t = new Task("Return Book", "deadline", LocalDate.parse("2012-09-23"));
        assertEquals(t.toString(), "[D][ ] Return Book(by Sep 23 2012)");
    }

    @Test
    public void Test3(){
        Task t = new Task("Borrow book", "todo", null);
        assertEquals(t.toString(), "[T][ ] Borrow book");
    }
}
