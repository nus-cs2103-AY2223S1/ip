package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import stubs.TaskListStub;
import stubs.TaskStub;

public class TodoCommandTest {

    private static final String MESSAGE_SUCCESS = "OK, I added this task to my list:\n %1$s";
    private static final String DESCRIPTION = "test";

    @Test
    public void testTodoCommand() {
        TaskListStub list = new TaskListStub(new ArrayList<>());
        TaskStub task = new TaskStub("[T][ ] test");

        TodoCommand command = new TodoCommand(DESCRIPTION);
        command.setData(list);

        CommandResult result = new CommandResult(String.format(MESSAGE_SUCCESS, list.addTask(task)), list);
        assertEquals(result.toString(), command.execute().toString());
    }
}
