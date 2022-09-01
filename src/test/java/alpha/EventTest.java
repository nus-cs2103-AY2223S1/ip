package alpha;

import alpha.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Event event = new Event("birthday", "Sep 04 2022", "E");
    Ui uI = new Ui();

    @Test
    public void getDate() {
        assertEquals("Sep 04 2022", event.getDate());
    }

    @Test
    public void toString_noInput_String() {
        assertEquals(String.format(uI.ANSI_BLUE + "[ %s ] [ %s ] %s", "E", " ", "birthday" + uI.ANSI_RESET) + String.format(uI.ANSI_RED + " (on: %s)", "Sep 04 2022" + uI.ANSI_RESET), event.toString());
    }
}
