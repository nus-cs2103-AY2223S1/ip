package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testBye() {
        Duke duke = new Duke();
        assertEquals("Bye! Shutting Down...", duke.getResponse("bye"));
    }

    @Test
    public void testDeadline() {
        Duke duke = new Duke();
        assertEquals("Invalid Date Entered", duke.getResponse("deadline test /by 134123"));
    }

    @Test
    public void testEvent() {
        Duke duke = new Duke();
        assertEquals(duke.getResponse("Event test /at abcd"), "Invalid Command");
    }


}
