package duke;

//import duke.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void getCommandTypeTest1() {
        String t1 = "todo A-Junit test";
        assertEquals("TODO", Parser.getCommandType(t1));
    }

    @Test
    public void getCommandTypeTest2() {
        String t2 = "todo";
        try {
            Parser.getCommandType(t2);
            Assertions.fail();
        } catch (IllegalArgumentException iae) {
            String expected = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            assertEquals(expected, iae.getMessage());
        }
    }

    @Test
    public void getCommandTypeTest3() {
        String t3 = "event CS2103 lecture /at 26/08/2022 1600";
        assertEquals("EVENT", Parser.getCommandType(t3));
    }

    @Test
    public void getCommandTypeTest4() {
        String t4 = "reminder CS2103 lecture /at 26/08/2022 1600";
        try {
            Parser.getCommandType(t4);
            Assertions.fail();
        } catch (IllegalArgumentException iae) {
            String expected = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            assertEquals(expected, iae.getMessage());
        }
    }
}