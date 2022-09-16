package duke.TaskListTest;

import duke.models.Deadline;
import duke.models.Task;
import duke.models.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    Task deadline = new Deadline("Testing", "2000-09-15");
    TaskList list = new TaskList();

    @Test
    public void addTaskTest() {
        deadline.markAsDone();
        list.add(deadline);
        assertEquals("[D][X] Testing (by: Sep 15 2000)", list.get(0).toString());
    }
}
