package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ByeCommandTest {
    @Test
    public void execute_acceptableExecution_success() {
        ByeCommand command = new ByeCommand();
        command.execute(new TaskListStub(), new UiStub(), new StorageStub("test.txt"));
        assertEquals(true, command.isExit());
    }
}
