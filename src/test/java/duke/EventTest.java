package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void dummyTest1() {
        Event task = new Event("meeting", false, "2022-12-05", "23:59");
        assertEquals("E | O | meeting | Dec 5 2022 1159PM", task.toString());
    }

    @Test
    public void dummyTest2() {
        Event task = new Event("meeting", false, "2022-12-05", "23:59");
        task.mark();
        assertEquals("E | X | meeting | Dec 5 2022 1159PM", task.toString());
    }
}
