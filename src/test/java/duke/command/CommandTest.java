package duke.command;

import duke.task.Todo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    @Test
    public void isExitTest() {
        assertEquals(new DeleteCommand(1).isExit(), false);
        assertEquals(new ExitCommand().isExit(), true);
        assertEquals(new ListCommand().isExit(), false);
        assertEquals(new MarkDoneCommand(1).isExit(), false);
        assertEquals(new UnmarkDoneCommand(1).isExit(), false);
    }
}
