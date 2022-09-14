package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import stubs.TaskListStub;
import stubs.TaskStub;

public class EventCommandTest {

    private static final String MESSAGE_SUCCESS = "OK, I added this task to my list:\n %1$s";
    private static final String DESCRIPTION = "test";
    private static final LocalDateTime DATE_TIME = LocalDateTime.now();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy h:mm a");

    @Test
    public void testDeadlineCommand() {
        TaskListStub list = new TaskListStub(new ArrayList<>());
        TaskStub task = new TaskStub("[E][ ] test (at: " + DATE_TIME.format(formatter) + ")");

        EventCommand command = new EventCommand(DESCRIPTION, DATE_TIME);
        command.setData(list);

        CommandResult result = new CommandResult(String.format(MESSAGE_SUCCESS, list.addTask(task)), list);
        assertEquals(command.execute().toString(), result.toString());
    }
}
