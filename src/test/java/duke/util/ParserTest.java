package duke.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import duke.command.ChangeStatusCommand;
import duke.task.Todo;

public class ParserTest {

    TaskList taskList = new TaskList();
    Parser parser = new Parser(null, null, taskList);

    @Test
    public void parseMarkCommand() {
        taskList.addTask(new Todo("task1"));
        assertAll(
                () -> assertEquals(new ChangeStatusCommand(null, null, null, "mark 1", true), parser.parse("mark 1")));
    }

    @Test
    public void parseInvalidUserCommand() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse("marek 2"));
    }

    @Test
    public void parseUnmarkCommand() {
        taskList.addTask(new Todo("task2"));
        assertAll(() -> assertEquals(new ChangeStatusCommand(null, null, null, "unmark 2", false),
                parser.parse("unmark 2")));
    }
}
