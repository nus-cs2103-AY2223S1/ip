package maria.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskTodoTest {

    @Test
    public void addTaskEventTest() {

        try {
            TaskTodo te = new TaskTodo("Kick_Ball", false);
            assertEquals("[Todo] Kick_Ball [Not Done]", te.toString());
        } catch (TaskNoNameException e) {
            fail();
        }

    }

}
