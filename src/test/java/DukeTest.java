import duke.*;
import duke.events.Task;
import duke.parser.Parser;
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
        Task todoTest = p.generateTask("todo borrow book");
        Task deadlineTest = p.generateTask("deadline return book /by 2019-10-15");
        Task eventTest = p.generateTask("event project meeting /at 2019-10-15");
        assertEquals(todoTest.toString(), "[T][ ] borrow book");
        assertEquals(deadlineTest.toString(), "[D][ ] return book (by: Oct 15 2019)");
        assertEquals(eventTest.toString(), "[E][ ] project meeting (at: Oct 15 2019)");

    }

    //Testing Task Marking/Unmarking in TaskList
    @Test
    public void TestMarkUnmark() {
        Task todoTest = Parser.generateTask("todo borrow book");
        List<Task> emptyTaskList = new ArrayList<Task>();
        TaskList tl = new TaskList(emptyTaskList);
        tl.addTask(todoTest);
        tl.markTask(0);
        assertEquals(todoTest.toString(), "[T][X] borrow book");
        tl.unmarkTask(0);
        assertEquals(todoTest.toString(), "[T][ ] borrow book");
    }
}