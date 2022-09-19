package duke.TaskListTest;

import duke.models.Deadline;
import duke.models.Task;
import duke.models.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTask() {
        Task deadline = new Deadline("Testing", "2000-09-15");
        TaskList list = new TaskList();
        list.add(deadline);
        assertEquals("[D][ ][ ] Testing (by: Sep 15 2000)", list.get(0).toString());
    }

    @Test
    public void deleteTask() {
        Task deadline = new Deadline("Testing", "2000-09-15");
        Task deadline2 = new Deadline("Testing2", "2000-09-16");
        TaskList list = new TaskList();
        list.add(deadline);
        list.add(deadline2);
        list.remove(0);
        assertEquals("[D][ ][ ] Testing2 (by: Sep 16 2000)", list.get(0).toString());
    }
}
