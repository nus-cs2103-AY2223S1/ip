package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void dummyTest1() {
        Event task = new Event("meeting", new String[]{" ", "2022-12-05", "09:00"});
        assertEquals("[E][ ] meeting (at: Dec 5 2022 09:00AM)", task.toString());
    }

    @Test
    public void dummyTest2() {
        Event task = new Event("meeting", new String[]{" ", "2022-12-05", "09:00"});
        task.mark();
        assertEquals("[E][X] meeting (at: Dec 5 2022 09:00AM)", task.toString());
    }

    @Test
    public void dummyTest3() {
        Event task = new Event("dinner", new String[]{" ", "2022-12-07", "18:30"});
        task.mark();
        task.unmark();
        assertEquals("[E][ ] dinner (at: Dec 7 2022 06:30PM)", task.toString());
    }
}
