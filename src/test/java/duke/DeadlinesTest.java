package duke;

import duke.tasks.Deadlines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {

    @Test
    public void DeadlinesTest() {
        assertEquals("[D][ ] homework (by: 2022-08-31)", new Deadlines("homework",  "2022-08-31").toString());
    }
}
