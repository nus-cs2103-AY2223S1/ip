import jarvis.task.Deadline;
import jarvis.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toDataFormTest() {
        Event event = new Event("eevvnt", "2022-09-15T18:00", true);
        assertEquals("E|1|eevvnt|2022-09-15T18:00\n", event.toDataForm());
    }
}
