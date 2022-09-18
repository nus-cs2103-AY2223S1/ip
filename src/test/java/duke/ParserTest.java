package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testBye() {
        Duke duke = new Duke();
        assertEquals(duke.getResponse("bye"), "Bye! Don't Come back!");
    }

    @Test
    public void testDeadline() {
        Duke duke = new Duke();
        assertEquals(duke.getResponse("deadline test /by 134123"), "Invalid Date Entered");
    }


}
