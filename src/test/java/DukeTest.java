import duke.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    //Testing Parser.GenerateTask() for the 3 different types of tasks
    // Also testing their toString() formatting
    @Test
    public void TestTaskGeneration() {
        Parser p = new Parser();
        Task todoTest = p.GenerateTask("todo borrow book");
        Task deadlineTest = p.GenerateTask("deadline return book /by 2019-10-15");
        Task eventTest = p.GenerateTask("event project meeting /at 2019-10-15");
        assertEquals(todoTest.toString(), "[T][ ] borrow book");
        assertEquals(deadlineTest.toString(), "[D][ ] return book (by: Oct 15 2019)");
        assertEquals(eventTest.toString(), "[E][ ] project meeting (at: Oct 15 2019)");

    }

    //Testing Task Marking/Unmarking in TaskList
    @Test
    public void TestMarkUnmark() {
        Task todoTest = Parser.GenerateTask("todo borrow book");
        List<Task> emptyTaskList = new ArrayList<Task>();
        TaskList tl = new TaskList(emptyTaskList);
        tl.AddTask(todoTest);
        tl.MarkTask(0);
        assertEquals(todoTest.toString(), "[T][X] borrow book");
        tl.UnmarkTask(0);
        assertEquals(todoTest.toString(), "[T][ ] borrow book");
    }
}