package sakura;

import org.junit.jupiter.api.Test;
import sakura.task.Event;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void testStringifyTask() {
        Event test1 = new Event("Zoukout Festival", "2022-05-15 2359");
        assertEquals("E|0|Zoukout Festival|2022-05-15 2359", test1.stringifyTask());
    }

    @Test
    void testMarkDone() {
        Event test2 = new Event("Zoukout Festival", "2022-05-15 2359");
        test2.markDone();
        assertEquals("E|1|Zoukout Festival|2022-05-15 2359", test2.stringifyTask());
    }

    @Test
    void testToString() {
        Event test1 = new Event("Zoukout Festival", "2022-05-15 2359");
        assertEquals("(EVENT)[ ] Zoukout Festival (at: 23:59, 15 May 2022)", test1.toString());

    }
}
