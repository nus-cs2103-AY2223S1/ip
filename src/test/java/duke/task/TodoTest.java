package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TodoTest {
    private Task task = new Todo(TaskType.TODO, "task123", true);

    @Test
    public void checkToString_taskInfo_returnString() {
        assertEquals(task.toString(), "[T][X] task123");
    }

    @Test
    public void checkTaskType_taskInfo_returnTaskType() {
        assertEquals(task.getTaskType(), TaskType.TODO);
    }

    @Test
    public void checkName_taskInfo_returnName() {
        assertEquals(task.getName(), "task123");
    }

    @Test
    public void checkIsMarked_taskInfo_returnBoolean() {
        assertTrue(task.isMarked());
    }
}
