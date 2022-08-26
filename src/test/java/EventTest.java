package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void descriptionTest() {
        String description = "soccer match";
        String at = "2/8/2020 0800";
        Event test = new Event(description, at);
        assertEquals("[E][ ]soccer match (at: Aug 02 2020 08:00)", test.toString());
    }
}
