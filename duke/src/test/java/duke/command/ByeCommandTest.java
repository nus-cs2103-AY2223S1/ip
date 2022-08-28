package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ByeCommandTest {
    @Test
    public void execute_acceptableExcecution_success() {
        ByeCommand command = new ByeCommand();
        command.execute(new TaskListStub(), new UiStub(), new StorageStub("test.txt"));
        assertEquals(true, command.isExit());
    }
}