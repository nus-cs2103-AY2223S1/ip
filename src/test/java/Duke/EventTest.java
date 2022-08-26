package Duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testMarkForEvent(){
        Task event = new Event("sleep","12-12-2022");
        assertEquals("[E][ ] sleep (at: Dec 12 2022)",event.toString() );
        event.markAsDone();
        assertEquals("[E][x] sleep (at: Dec 12 2022)",event.toString());
    }

    @Test
    public void testUnmarkForEvent(){
        Task event = new Event("sleep","12-12-2022");
        assertEquals("[E][ ] sleep (at: Dec 12 2022)",event.toString());
        event.markAsDone();
        assertEquals("[E][x] sleep (at: Dec 12 2022)",event.toString());
        event.UnmarkAsDone();
        assertEquals("[E][ ] sleep (at: Dec 12 2022)",event.toString());
    }
}
