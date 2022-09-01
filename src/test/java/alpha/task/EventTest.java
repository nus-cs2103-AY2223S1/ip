package alpha.task;

import alpha.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    Event event = new Event("birthday", "Sep 04 2022", "E");
    Ui uI = new Ui();

    /**
     * Tests whether the getDate() in Event class returns the date in the required format or not.
     */
    @Test
    public void getDate() {
        assertEquals("Sep 04 2022", event.getDate());
    }

    /**
     * Tests whether the toString() in Event class returns the event details in the required format or not.
     */
    @Test
    public void toString_noInput_String() {
        assertEquals(String.format(uI.getANSI_CODE("ANSI_BLUE") + "[ %s ] [ %s ] %s", "E", " ", "birthday"
                + uI.getANSI_CODE("")) + String.format(uI.getANSI_CODE("ANSI_RED") + " (on: %s)", "Sep 04 2022"
                + uI.getANSI_CODE("")), event.toString());
    }
}
