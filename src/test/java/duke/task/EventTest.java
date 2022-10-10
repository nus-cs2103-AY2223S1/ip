package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void eventTest() {
        Event e = new Event("something", "in the afternoon");
        assertEquals(e.toString(), "[E] [ ] something (at: in the afternoon)");
    }

    @Test
    public void eventTest2() {
        Event e = new Event("something", "in the afternoon");
        e.mark();
        assertEquals(e.toString(), "[E] [x] something (at: in the afternoon)");
    }

    @Test
    public void eventTest3() {
        Event e = new Event("something", "in the afternoon");
        e.mark();
        e.unmark();
        assertEquals(e.toString(), "[E] [ ] something (at: in the afternoon)");
    }

}
