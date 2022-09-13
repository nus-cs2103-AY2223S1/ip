package bro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * ParserTest class.
 */
public class ParserTest {
    /**
     * Tests whether if deadline date is invalid throws exception.
     */
    @Test
    public void deadlineParser_invalidDateFormat_returnsBroException() {
        Parser parser = new Parser();
        try {
            parser.timeParser("11-11-2022");
            fail();
        } catch (BroException e) {
            assertEquals("Please enter the date in the format dd/MM/yyyy kkmm", e.getMessage());
        }
    }

    /**
     * Tests whether the Task description when given empty description
     * throws exception if its in invalid format.
     */
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

    /**
     * Tests whether the Task description throws exception if its in invalid format.
     */
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
