package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void printEventTest() {

        assertEquals("E |   | birthday | 1 Jan 2022",
                new Event("birthday", false, "1 Jan 2022").toString());
    }

    @Test
    public void markEventTest() {
        Task event = new Event("birthday", false, "1 Jan 2022");
        event.markAsDone();
        assertEquals("E | X | birthday | 1 Jan 2022", event.toString());
    }

    @Test
    public void unmarkEventTest() {
        Task event = new Event("birthday", true, "1 Jan 2022");
        event.markAsUndone();
        assertEquals("E |   | birthday | 1 Jan 2022", event.toString());
    }

}