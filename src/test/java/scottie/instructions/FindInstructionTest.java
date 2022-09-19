package scottie.instructions;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import scottie.tasks.Task;
import scottie.tasks.TaskList;
import scottie.tasks.TaskListMock;
import scottie.tasks.Todo;
import ui.UiMock;

class FindInstructionTest {
    @Test
    void testExecuteOneMatch() {
        TaskList taskListMock = new TaskListMock();
        UiMock uiMock = new UiMock();

        Task todo0 = new Todo("Test todo 0");
        Task todo1 = new Todo("Test todo 1");
        Task todo2 = new Todo("Test todo 2");
        taskListMock.addTask(todo0);
        taskListMock.addTask(todo1);
        taskListMock.addTask(todo2);

        try {
            Instruction findInstruction =
                    Instruction.of("find", TaskListMock.MATCH_FIRST_TASK_SEARCH_TEXT, Map.of());
            findInstruction.execute(taskListMock, uiMock);
            uiMock.assertMessagesShown(List.of(
                    "Ok, I've found this task in your list:",
                    String.format("OrderedList(%s)", todo0)));
        } catch (InvalidCommandException e) {
            fail("An unexpected exception occurred while running this test.");
        }
    }

    @Test
    void testExecuteNoMatches() {
        TaskList taskListMock = new TaskListMock();
        UiMock uiMock = new UiMock();

        Task todo0 = new Todo("Test todo 0");
        Task todo1 = new Todo("Test todo 1");
        Task todo2 = new Todo("Test todo 2");
        taskListMock.addTask(todo0);
        taskListMock.addTask(todo1);
        taskListMock.addTask(todo2);

        try {
            Instruction findInstruction =
                    Instruction.of("find", TaskListMock.MATCH_NO_TASK_SEARCH_TEXT, Map.of());
            findInstruction.execute(taskListMock, uiMock);
            uiMock.assertMessagesShown(List.of("Hmm... looks like there aren't any matching tasks."));
        } catch (InvalidCommandException e) {
            fail("An unexpected exception occurred while running this test.");
        }
    }
}
