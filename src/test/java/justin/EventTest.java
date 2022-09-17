package justin;

import justin.command.AddEventCommand;
import justin.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void eventTest1() {
        Event task = new Event("meeting", false, "2022-12-05", "23:59");
        assertEquals("E | Undone | meeting | Dec 5 2022 1159PM", task.toString());
    }

    @Test
    public void eventTest2() {
        Event task = new Event("meeting", false, "2022-12-05", "23:59");
        task.mark();
        assertEquals("E | Done! | meeting | Dec 5 2022 1159PM", task.toString());
    }
}
