package kohaku;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import kohaku.commands.MarkDoneCommand;
import kohaku.task.Task;
import kohaku.task.ToDo;

public class MarkDoneCommandTest {
    /**
     * Test that mark done command is able to mark the task as done.
     */
    @Test
    public void execute_task_markDone() {
        Task task = new ToDo("test");
        MarkDoneCommand command = new MarkDoneCommand(task);
        assertEquals(command.execute(), "Yatta~ Congrats master, you've completed this task!\n[T][X] test");
    }
}
