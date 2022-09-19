package scottie.instructions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import scottie.tasks.Task;
import scottie.tasks.TaskList;
import scottie.tasks.TaskListMock;
import scottie.tasks.Todo;
import ui.UiMock;

class UnmarkInstructionTest {
    @Test
    void testExecute() {
        TaskList taskListMock = new TaskListMock();
        UiMock uiMock = new UiMock();

        Task todo0 = new Todo("Test todo 0");
        Task todo1 = new Todo("Test todo 1");
        Task todo2 = new Todo("Test todo 2");
        taskListMock.addTask(todo0);
        taskListMock.addTask(todo1);
        taskListMock.addTask(todo2);
        taskListMock.markTask(0);

        try {
            Instruction.of("unmark", "1", Map.of()).execute(taskListMock, uiMock);
            assertFalse(taskListMock.isMarked(0));
            uiMock.assertMessagesShown(List.of("Aww man, ok guess I'll mark task 1 as not done:", todo0.toString()));
        } catch (InvalidCommandException e) {
            fail("An unexpected exception occurred while running this test.");
        }
    }
}
