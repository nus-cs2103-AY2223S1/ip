package duke.TaskListTest;

import duke.exceptions.DukeException;
import duke.models.Deadline;
import duke.models.FormattedDate;
import duke.models.Task;
import duke.models.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTask() throws DukeException {
        FormattedDate testDate = new FormattedDate("2000-09-15");
        Task deadline = new Deadline("Testing", testDate);
        TaskList list = new TaskList();
        list.add(deadline);
        assertEquals("[D][ ][ ] Testing (by: Sep 15 2000)", list.get(0).toString());
    }

    @Test
    public void deleteTask() throws DukeException {
        FormattedDate testDate = new FormattedDate("2000-09-16");
        Task deadline = new Deadline("Testing", testDate);
        Task deadline2 = new Deadline("Testing2", testDate);
        TaskList list = new TaskList();
        list.add(deadline);
        list.add(deadline2);
        list.remove(0);
        assertEquals("[D][ ][ ] Testing2 (by: Sep 16 2000)", list.get(0).toString());
    }
}
