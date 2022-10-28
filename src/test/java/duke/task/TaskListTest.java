package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void testSortDeadlines() {
        TaskList taskList = new TaskList();
        Deadline deadline1 = new Deadline("deadline1", "2022-08-24");
        Deadline deadline2 = new Deadline("deadline2", "2022-08-22");
        Deadline deadline3 = new Deadline("deadline3", "2022-08-25");
        taskList.add(deadline1);
        taskList.add(deadline2);
        taskList.add(deadline3);

        assertEquals(
                "Here are the tasks in your list:\n" + "1.[D][ ] deadline2 (by: Aug 22 2022 0000)\n"
                        + "2.[D][ ] deadline1 (by: Aug 24 2022 0000)\n" + "3.[D][ ] deadline3 (by: Aug 25 2022 0000)\n",
                taskList.sortDeadlines().toString());
    }

}
