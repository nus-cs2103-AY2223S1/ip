package duke;

import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Event;
import task.Task;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void emptyTaskList_givesZeroLength() {
        assertEquals(new TaskList().getLength(), 0);
    }

    @Test
    public void addDeadlineTask_addsTask() {
        TaskList ls = new TaskList();
        Task t = new Deadline("test", LocalDate.parse("2022-12-12"));
        ls.addTask(t, false);
        assertEquals(ls.getTaskAt(0), t);
    }

    @Test
    public void addEventTask_addsTask() {
        TaskList ls = new TaskList();
        Task t = new Event("test2", LocalDate.parse("2022-11-12"));
        ls.addTask(t, false);
        assertEquals(ls.getTaskAt(0), t);
    }

    @Test
    public void listCommand_isWorking() {
        TaskList ls = new TaskList();
        Task t = new Deadline("Deadline test", LocalDate.parse("2022-01-17"));
        Task t2 = new Event("Event desc", LocalDate.parse("2012-10-12"));
        ls.addTask(t, false);
        ls.addTask(t2, false);
        Parser p = new Parser(ls);
        assertEquals("Here are your tasks:\n" +
                "1. [D][ ] Deadline test (by: Jan 17 2022)\n" +
                "2. [E][ ] Event desc (at: Oct 12 2012)\n", p.parseUserInput("list"));
    }
}
