package poolsheen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import poolsheen.command.Command;

public class ParserTest {

    public class ByeCommandStub extends Command {
        public ByeCommandStub() {
            super(true, new ArrayList<String>());
        }

        @Override
        public String execute(TaskList tl, Ui ui, Storage storage) {
            return "Line should not reach here";
        }
    }

    @Test
    public void parse_byeCommand_success() {
        ByeCommandStub bcs = new ByeCommandStub();
        assertEquals(true, bcs.isExit());
    }
}
