package scottie.instructions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import scottie.tasks.TaskListMock;
import ui.UiMock;

class SortInstructionTest {
    @Test
    void testExecuteDescription() {
        TaskListMock taskListMock = new TaskListMock();
        UiMock uiMock = new UiMock();
        try {
            Instruction.of("sort", null, Map.of("by", "description")).execute(taskListMock, uiMock);
            assertEquals("description", taskListMock.getSortOrder());
            uiMock.assertMessagesShown(List.of("Ok, I've sorted your tasks in alphabetical order."));
        } catch (InvalidCommandException e) {
            fail("An unexpected exception occurred while running this test.");
        }
    }

    @Test
    void testExecuteDescriptionReversed() {
        TaskListMock taskListMock = new TaskListMock();
        UiMock uiMock = new UiMock();
        try {
            Instruction.of("sort", null, Map.of("desc", "")).execute(taskListMock, uiMock);
            assertEquals("description reversed", taskListMock.getSortOrder());
            uiMock.assertMessagesShown(List.of("Ok, I've sorted your tasks in reverse alphabetical order."));
        } catch (InvalidCommandException e) {
            fail("An unexpected exception occurred while running this test.");
        }
    }

    @Test
    void testExecuteDate() {
        TaskListMock taskListMock = new TaskListMock();
        UiMock uiMock = new UiMock();
        try {
            Instruction.of("sort", null, Map.of("by", "date")).execute(taskListMock, uiMock);
            assertEquals("date", taskListMock.getSortOrder());
            uiMock.assertMessagesShown(List.of("Ok, I've sorted your tasks in chronological order."));
        } catch (InvalidCommandException e) {
            fail("An unexpected exception occurred while running this test.");
        }
    }

    @Test
    void testExecuteDateReversed() {
        TaskListMock taskListMock = new TaskListMock();
        UiMock uiMock = new UiMock();
        try {
            Instruction.of("sort", null, Map.of("by", "date", "desc", "")).execute(taskListMock, uiMock);
            assertEquals("date reversed", taskListMock.getSortOrder());
            uiMock.assertMessagesShown(List.of("Ok, I've sorted your tasks in reverse chronological order."));
        } catch (InvalidCommandException e) {
            fail("An unexpected exception occurred while running this test.");
        }
    }
}
