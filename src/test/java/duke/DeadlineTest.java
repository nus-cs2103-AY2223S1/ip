package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void DeadlineObjectTest() {
        Deadline deadlineObject = new Deadline("Deadline", "2021-07-24");
        String actualOutput = "[D][ ] Deadline (by: Jul 24 2021)";
        assertEquals(deadlineObject.toString(), actualOutput);
    }
}
