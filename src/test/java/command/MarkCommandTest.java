package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import stubs.TaskListStub;
import stubs.TaskStub;

public class MarkCommandTest {

    private static final String MESSAGE_SUCCESS = "I'VE MARKED THIS TASK AS DONE:\n %1$s";

    @Test
    public void testMarkCommand() {
        TaskListStub taskList = new TaskListStub(new ArrayList<>());
        TaskStub stub = new TaskStub(false);
        taskList.addTask(stub);

        MarkCommand command = new MarkCommand(0);
        command.setData(taskList);

        CommandResult result = new CommandResult(String.format(MESSAGE_SUCCESS, stub));
        assertEquals(result.toString(), command.execute().toString());
    }
}
