package scottie.instructions;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import scottie.tasks.Task;
import scottie.tasks.TaskList;
import scottie.tasks.TaskListMock;
import ui.UiMock;

class DeadlineInstructionTest {
    @Test
    void testExecute() {
        TaskList taskListMock = new TaskListMock();
        UiMock uiMock = new UiMock();
        try {
            Instruction deadlineInstruction =
                    Instruction.of("deadline", "deadline test description", Map.of("by", "1/4/16"));
            deadlineInstruction.execute(taskListMock, uiMock);
            Task deadline = taskListMock.getTask(0);
            uiMock.assertMessagesShown(List.of("Ok got it, I've added this deadline:", deadline.toString()));
        } catch (InvalidCommandException e) {
            fail("An unexpected exception occurred while running this test.");
        }
    }
}
