import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ip.exception.NoTaskFound;
import ip.utility.TaskList;

public class TaskListTest {
    private final TaskList taskList = new TaskList();

    @Test
    public void deleteTest_negativeIndex_throwNoTaskFound() {
        try {
            taskList.delete(-1);
        } catch (Exception e) {
            assertTrue(e instanceof NoTaskFound);
        }
    }

    @Test
    public void markTest_outOfRangeIndex_throwNoTaskFound() {
        try {
            taskList.mark(5);
        } catch (Exception e) {
            assertTrue(e instanceof NoTaskFound);
        }
    }
}
