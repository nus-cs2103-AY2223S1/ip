package scottie.instructions;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import scottie.tasks.Deadline;
import scottie.tasks.Event;
import scottie.tasks.Task;
import scottie.tasks.TaskList;
import scottie.tasks.TaskListMock;
import scottie.tasks.Todo;
import ui.UiMock;

class ListInstructionTest {
    @Test
    void testExecuteEmptyList() {
        TaskList taskListMock = new TaskListMock();
        UiMock uiMock = new UiMock();
        try {
            Instruction.of("list", null, Map.of()).execute(taskListMock, uiMock);
            uiMock.assertMessagesShown(List.of("Wow! Looks like you have no tasks at the moment!\n"
                    + "I wish I had no tasks to do too..."));
        } catch (InvalidCommandException e) {
            fail("An unexpected exception occurred while running this test.");
        }
    }

    @Test
    void testExecuteNonEmptyList() {
        TaskList taskListMock = new TaskListMock();
        UiMock uiMock = new UiMock();

        Task todo = new Todo("Test todo");
        Task deadline = new Deadline("Test deadline", LocalDateTime.of(2012, 11, 5, 15, 55));
        Task event = new Event("Test event", LocalDate.of(2099, 1, 31));
        taskListMock.addTask(todo);
        taskListMock.addTask(deadline);
        taskListMock.addTask(event);

        try {
            Instruction.of("list", null, Map.of()).execute(taskListMock, uiMock);
            uiMock.assertMessagesShown(List.of(
                    "Ok, here's your list of tasks at the moment!",
                    String.format("OrderedList(%s, %s, %s)", todo, deadline, event)));
        } catch (InvalidCommandException e) {
            fail("An unexpected exception occurred while running this test.");
        }
    }
}
