package duke.commands;

import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UndoCommandTest {

    @Test
    public void undoTest() {
        Command dummyCmd = new Command() {
            @Override
            public String execute() {
                return "execute";
            }

            @Override
            public String undo() {
                return "undo";
            }
        };
        Deque<Command> hist = new LinkedList<>();

        dummyCmd.execute();
        hist.add(dummyCmd);
        UndoCommand undoCmd = new UndoCommand(hist);
        String response = undoCmd.execute();
        assertEquals(response, "undo");
    }

}
