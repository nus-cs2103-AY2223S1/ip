import duke.task.Events;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {

    /**
     * Test Case 1. toString() method.
     * Tests for an Event task with description.
     */
    @Test
    public void toString_description_stringIsFormatted() {
        String str = "2022-10-11";
        Events event = new Events("Event", LocalDate.parse(str));
        assertEquals("[E][] Event (at: 11 OCTOBER 2022)", event.toString());
    }

    /**
     * Test Case 2. writeData method.
     * Tests for an Event task to see if data is written correctly.
     */
    @Test
    public void writeData_saveTask_stringIsFormatted() {
        String str = "2022-10-11";
        Events event = new Events("Event", LocalDate.parse(str));
        assertEquals("E#0#Event#2022-10-11", event.writeData());
    }
}
