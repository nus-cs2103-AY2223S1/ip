package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    @Test
    public void setExitTest() {
        ListCommand listCommand = new ListCommand();
        listCommand.setExit();
        assertEquals(listCommand.getExit(), true);
    }
}
