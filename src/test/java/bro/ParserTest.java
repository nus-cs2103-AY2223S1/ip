package bro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void deadlineParser_invalidDateFormat_returnsBroException() {
        Parser parser = new Parser();
        try {
            parser.deadlineParser("11-11-2022");
            fail();
        } catch (BroException e) {
            assertEquals("Please enter the date in the format dd/MM/yyyy kkmm", e.getMessage());
        }
    }

    @Test
    public void checkEmptyInput_invalidTaskDescription_returnsBroException() {
        Parser parser = new Parser();
        try {
            parser.parse("event");
            fail();
        } catch (BroException e) {
            assertEquals("The description cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void checkInput_invalidTaskDescription_returnsBroException() {
        Parser parser = new Parser();
        try {
            parser.parse("deadline CS2109S /by ");
            fail();
        } catch (BroException e) {
            assertEquals("Please give the description and time!", e.getMessage());
        }
    }

}
