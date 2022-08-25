package poolsheen.command;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByeCommandTest {

    @Test
    public void isExit_true() {
        Command byeCommand = new ByeCommand(new ArrayList<String>(100));
        assertEquals(true, byeCommand.isExit());
    }
}
