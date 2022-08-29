import org.junit.jupiter.api.Test;
import ted.Storage;
import ted.command.*;
import ted.exception.TedException;
import ted.task.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {
    @Test
    public void deadlineCommand_ThrowsException_IfArgIsEmpty() {
        assertThrows(TedException.class, () -> new DeadlineCommand(""));
    }

    @Test
    public void deadlineCommand_ThrowsException_IfByIsNotPresent() {
        assertThrows(TedException.class, () -> new DeadlineCommand("hello world"));
    }

    @Test
    public void deadlineCommand_ThrowsException_IfDateIsEmpty() {
        assertThrows(TedException.class, () -> new DeadlineCommand("hello world /by"));
    }

    @Test
    public void deadlineCommand_ThrowsException_IfDateIsInvalid() {
        try {
            Storage storage = new StorageStub("");
            UiStub ui = new UiStub();
            DeadlineCommand command = new DeadlineCommand("hello world /by 20231-21");

            assertThrows(TedException.class, () -> command.run(TaskList.empty(), ui, storage));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void deadlineCommand_OutputMessage_IfDateIsValid() {
        Storage storage = new StorageStub("");
        UiStub ui = new UiStub();
        try {
            DeadlineCommand command = new DeadlineCommand("hello world /by 2022-03-02");
            command.run(TaskList.empty(), ui, storage);
        } catch (Exception e) {}

        assertEquals(
                String.format("Got it. I've added this task:\n"
                        + "[D][ ] hello world (by: 02/03/2022)\n"
                        + "Now you have 1 tasks in the list.\n"),
                ui.getLastMessage()
        );
    }

    @Test
    public void deleteCommand_ThrowException_IfArgsIsEmpty() {
        assertThrows(TedException.class, () -> new DeleteCommand(""));
    }

    @Test
    public void deleteCommand_ThrowException_IfArgsIsNotInteger() {
        assertThrows(TedException.class, () -> new DeleteCommand("Hello"));
    }

    @Test
    public void deleteCommand_DoesNotThrowException_IfArgsIsInteger() {
        assertDoesNotThrow(() -> new DeleteCommand("1"));
    }

    @Test
    public void deleteCommand_TaskIsEmpty() {
        Storage storage = new StorageStub("");
        UiStub ui = new UiStub();
        ArrayList<Task> tasks = new ArrayList<>();
        Task deletedTask = new ToDo("test");
        tasks.add(deletedTask);
        TaskList taskList = new TaskList(tasks);

        try {
            DeleteCommand command = new DeleteCommand("1");
            command.run(taskList, ui, storage);
        } catch (Exception e) {}

        assertEquals(0, taskList.size());
        assertEquals(
                String.format("Noted. I've removed this task:\n"
                        + "%s\n"
                        + "Now you have %d tasks in the list.\n", deletedTask, tasks.size()),
                ui.getLastMessage()
        );
    }

    @Test
    public void eventCommand_ThrowException_IfArgIsEmpty() {
        assertThrows(TedException.class, () -> new EventCommand(""));
    }

    @Test
    public void eventCommand_ThrowException_IfAtIsNotPresent() {
        assertThrows(TedException.class, () -> new EventCommand("hello world"));
    }

    @Test
    public void listCommand_OutputMessage() {
        Storage storage = new StorageStub("");
        UiStub ui = new UiStub();
        try {
            ListCommand command = new ListCommand("");
            command.run(TaskList.empty(), ui, storage);
        } catch (Exception e) {}

        assertEquals(
                String.format("There is no tasks here. Feel free to add some tasks.\n"),
                ui.getLastMessage()
        );
    }

    @Test
    public void markCommand_ThrowException_IfArgsIsNotInteger() {
        assertThrows(TedException.class, () -> new MarkCommand("1h"));
    }

    @Test
    public void markCommand_OutputMessage_IfArgsIsValid() {
        Storage storage = new StorageStub("");
        UiStub ui = new UiStub();
        ArrayList<Task> tasks = new ArrayList<>();
        Task markTask = new ToDo("test");
        tasks.add(markTask);
        TaskList taskList = new TaskList(tasks);

        try {
            MarkCommand command = new MarkCommand("1");
            command.run(taskList, ui, storage);
        } catch (Exception e) {}

        assertEquals(
                String.format("[T][X] test"),
                markTask.toString()
        );
    }
}
