package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] example (by: Aug 14 2022)",
                new Deadline("example", "2022-08-14").toString());
    }

    @Test
    public void testDataEntryConversion() {
        assertEquals("D # 0 # example # Aug 14 2022\n",
                new Deadline("example", "2022-08-14").toDataEntry());
    }
}
