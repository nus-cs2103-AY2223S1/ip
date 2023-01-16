package scottie.instructions;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import scottie.tasks.Task;
import scottie.tasks.TaskList;
import scottie.tasks.TaskListMock;
import ui.UiMock;

class TodoInstructionTest {
    @Test
    void testExecute() {
        TaskList taskListMock = new TaskListMock();
        UiMock uiMock = new UiMock();
        try {
            Instruction todoInstruction = Instruction.of("todo", "todo test description", Map.of());
            todoInstruction.execute(taskListMock, uiMock);
            Task todo = taskListMock.getTask(0);
            uiMock.assertMessagesShown(List.of("Ok got it, I've added this to-do:", todo.toString()));
        } catch (InvalidCommandException e) {
            fail("An unexpected exception occurred while running this test.");
        }
    }
}
