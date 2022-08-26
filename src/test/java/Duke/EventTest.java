package Duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testMarkForEvent(){
        Task deadline = new Event("sleep","12-12-2022");
        assertEquals(deadline.toString(), "[E][ ] sleep (at: Dec 12 2022)");
        deadline.markAsDone();
        assertEquals(deadline.toString(), "[E][x] sleep (at: Dec 12 2022)");
    }

    @Test
    public void testUnmarkForEvent(){
        Task deadline = new Deadline("sleep","12-12-2022");
        assertEquals(deadline.toString(), "[E][ ] sleep (at: Dec 12 2022)");
        deadline.markAsDone();
        assertEquals(deadline.toString(), "[E][x] sleep (at: Dec 12 2022)");
        deadline.UnmarkAsDone();
        assertEquals(deadline.toString(), "[D][ ] sleep (by: Dec 12 2022)");
    }
}
