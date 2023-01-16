package scottie.instructions;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import scottie.tasks.TaskListMock;
import ui.UiMock;

class ByeInstructionTest {
    @Test
    void testExecute() {
        UiMock uiMock = new UiMock();
        try {
            Instruction.of("bye", "", Map.of()).execute(new TaskListMock(), uiMock);
            uiMock.assertMessagesShown(List.of("Ok, see ya buddy!"));
            uiMock.assertProgramEnded();
        } catch (InvalidCommandException e) {
            fail("An unexpected exception occurred while running this test.");
        }
    }
}
