package duke;

import exception.EmptyDescriptionException;
import exception.InvalidCommandException;
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

}
