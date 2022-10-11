package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.Parser.addTaskParser;
import static seedu.duke.Parser.parse;

import seedu.duke.command.DeadlineCommand;
import seedu.duke.command.ToDoCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.list.TaskList;

public class ParserTest {

    @Test
    public void addTaskTest() {
        try {
            TaskList list = new TaskList();
            ToDoCommand testCommand1 = new ToDoCommand("eat");
            String testOutput1 = testCommand1.execute(list);

            assertEquals(testOutput1, parse("todo eat").execute(list));
            assertEquals(testOutput1, addTaskParser("todo", "eat").execute(list));

            DeadlineCommand testCommand2 = new DeadlineCommand("eat", "tomorrow");
            String testOutput2 = testCommand2.execute(list);

            assertEquals(testOutput2, parse("deadline eat /by tomorrow").execute(list));
            assertEquals(testOutput2, addTaskParser("deadline", "eat /by tomorrow").execute(list));

        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
