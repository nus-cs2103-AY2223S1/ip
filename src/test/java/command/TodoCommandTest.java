package command;

import henry.Task;
import henry.TaskList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoCommandTest {

    public static final String MESSAGE_SUCCESS = "OK. I ADDED THIS TASK TO MY LIST:\n\t\t\t %1$s.";
    TaskList list = new TaskList(new ArrayList<>());
    TodoCommand command = new TodoCommand("test");
    Task task = new Task(Commands.TODO, "test", null);
    CommandResult result = new CommandResult(String.format(MESSAGE_SUCCESS, list.addTask(task)), list);

    @Test
    public void executeTest() {
        command.setData(list);
        assertEquals(command.execute().toString(), result.toString());
    }
}
