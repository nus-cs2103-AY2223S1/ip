package duke.command;

import org.junit.jupiter.api.Test;

import duke.util.Parser;
import duke.util.TaskList;

public class FindCommandTest extends CommandTest {


    @Test
    public void task1() {
        TaskList wholeTaskList = Parser.parseTaskList(this.wholeFormattedTasklist);
    }
}
