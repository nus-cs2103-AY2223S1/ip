package alpha.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import alpha.Ui;




public class EventTest {

    private Event event = new Event("birthday", "Sep 04 2022", "E");
    private Ui uI = new Ui();

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
    public void toString_noInput_string() {
        assertEquals(String.format(uI.getAnsiCode("ANSI_BLUE") + "[ %s ] [ %s ] %s", "E", " ", "birthday"
                + uI.getAnsiCode("")) + String.format(uI.getAnsiCode("ANSI_RED") + " (on: %s)", "Sep 04 2022"
                + uI.getAnsiCode("")), event.toString());
    }
}
