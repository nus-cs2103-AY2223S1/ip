package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import stubs.TaskListStub;
import stubs.TaskStub;

public class UnmarkCommandTest {

    private static final String MESSAGE_SUCCESS = "I've marked this task as not done:\n %1$s";

    @Test
    public void testMarkCommand() {
        TaskListStub taskList = new TaskListStub(new ArrayList<>());
        TaskStub stub = new TaskStub(false);
        taskList.addTask(stub);

        UnmarkCommand command = new UnmarkCommand(0);
        command.setData(taskList);

        CommandResult result = new CommandResult(String.format(MESSAGE_SUCCESS, stub));
        assertEquals(result.toString(), command.execute().toString());
    }
}
