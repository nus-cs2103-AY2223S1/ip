package scottie.instructions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import scottie.tasks.Task;
import scottie.tasks.TaskList;
import scottie.tasks.TaskListMock;
import scottie.tasks.Todo;
import ui.UiMock;

class DeleteInstructionTest {
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
            Instruction.of("delete", "2", Map.of()).execute(taskListMock, uiMock);
            assertSame(todo0, taskListMock.getTask(0));
            assertSame(todo2, taskListMock.getTask(1));
            assertEquals(2, taskListMock.size());
            uiMock.assertMessagesShown(List.of(
                    "Ok got it, I've deleted this task:",
                    todo1.toString(),
                    "Looks like you have 2 tasks left in your list."));
        } catch (InvalidCommandException e) {
            fail("An unexpected exception occurred while running this test.");
        }
    }
}
