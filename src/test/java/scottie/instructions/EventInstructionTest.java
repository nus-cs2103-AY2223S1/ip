package scottie.instructions;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import scottie.tasks.Task;
import scottie.tasks.TaskList;
import scottie.tasks.TaskListMock;
import ui.UiMock;

class EventInstructionTest {
    @Test
    void testExecute() {
        TaskList taskListMock = new TaskListMock();
        UiMock uiMock = new UiMock();
        try {
            Instruction eventInstruction =
                    Instruction.of("event", "event test description", Map.of("at", "21/7/03"));
            eventInstruction.execute(taskListMock, uiMock);
            Task event = taskListMock.getTask(0);
            uiMock.assertMessagesShown(List.of("Ok got it, I've added this event:", event.toString()));
        } catch (InvalidCommandException e) {
            fail("An unexpected exception occurred while running this test.");
        }
    }
}
