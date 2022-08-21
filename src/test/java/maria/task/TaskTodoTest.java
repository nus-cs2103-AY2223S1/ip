package maria.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTodoTest {

    @Test
    public void addTaskEventTest() {

        try {
            TaskTodo te = new TaskTodo("Kick_Ball", false);
            assertEquals(te.toString(), "[T][ ] Kick_Ball");
        } catch (TaskNoNameException e) {
            fail();
        }

    }

}
