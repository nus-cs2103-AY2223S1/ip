package poolsheen.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ByeCommandTest {

    @Test
    public void isExit_true() {
        Command byeCommand = new ByeCommand(new ArrayList<String>(100));
        assertEquals(true, byeCommand.isExit());
    }
}
