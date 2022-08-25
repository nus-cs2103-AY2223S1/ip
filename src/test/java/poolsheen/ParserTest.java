package poolsheen;

import org.junit.jupiter.api.Test;

import poolsheen.command.Command;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    public class ByeCommandStub extends Command {
        public ByeCommandStub() {
            super(true, new ArrayList<String>());
        }

        @Override
        public void execute(TaskList tl, Ui ui, Storage storage) {
            System.out.println("Line should not reach here");
        }
    }

    @Test
    public void parse_byeCommand_success() {
        ByeCommandStub bcs = new ByeCommandStub();
        assertEquals(true, bcs.isExit());
    }
}
