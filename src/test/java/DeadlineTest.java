package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void descriptionTest() {
        String description = "homework";
        String by = "1/3/2019 1500";
        Deadline test = new Deadline(description, by);
        assertEquals("[D][ ]homework (by: Mar 01 2019 15:00)", test.toString());
    }
}
