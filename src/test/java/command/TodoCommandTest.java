package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import henry.Task;
import henry.TaskList;

public class TodoCommandTest {

    private static final String MESSAGE_SUCCESS = "OK. I ADDED THIS TASK TO MY LIST:\n\t\t\t %1$s.";

    @Test
    public void todoCommand_AddTodo_IfTodoIsWorking() {
        TaskList list = new TaskList(new ArrayList<>());
        TodoCommand command = new TodoCommand("test");
        Task task = new Task(Commands.TODO, "test", null);
        CommandResult result = new CommandResult(String.format(MESSAGE_SUCCESS, list.addTask(task)), list);
        command.setData(list);
        assertEquals(command.execute().toString(), result.toString());
    }
}
