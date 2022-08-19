package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testToString() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("abc", false));
        taskList.addTask(new Event("def ghi", "12/12/2022 12:12"));
        taskList.addTask(new Deadline("jkl mno pqr", "1/1/1 1:01", true));
        String expected = "1. [T][ ] abc\n" + 
                "2. [E][ ] def ghi (at: 12/12/2022 12:12)\n" + 
                "3. [D][✓] jkl mno pqr (by: 1/1/1 1:01)";
        assertEquals(expected, taskList.toString());
    }
}
