package scottie.instructions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import scottie.tasks.Task;
import scottie.tasks.TaskList;
import scottie.tasks.TaskListMock;
import scottie.tasks.Todo;
import ui.UiMock;

class MarkInstructionTest {
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

        try {
            Instruction.of("mark", "3", Map.of()).execute(taskListMock, uiMock);
            assertTrue(taskListMock.isMarked(2));
            uiMock.assertMessagesShown(List.of("Nice! I'll mark task 3 as done:", todo2.toString()));
        } catch (InvalidCommandException e) {
            fail("An unexpected exception occurred while running this test.");
        }
    }
}
