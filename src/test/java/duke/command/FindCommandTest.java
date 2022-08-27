package duke.command;

import duke.util.Parser;
import duke.util.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FindCommandTest extends CommandTest {


    @Test
    public void task1() {
        TaskList wholeTaskList= Parser.parseTaskList(this.WHOLE_FORMATTED_TASKLIST);
//        Storage storage = new Storage("")

    }
}
