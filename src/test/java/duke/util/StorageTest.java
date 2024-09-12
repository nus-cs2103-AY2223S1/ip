package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StorageTest {
    @Test
    public void formatStringTest() {
        String[] testOutputs = new String[] {"[T][X] Clear Linear Algebra Homework",
                                             "[D][ ] Cook Dinner (by: May 3 2019)",
                                             "[E][X] Attend Taylor Swift Concert (at: Feb 2 2023)"};

        for (int i = 0; i < testOutputs.length; i++) {
            Storage s = new Storage("./data/testing.txt");
            assertEquals(testOutputs[i], s.formatString(testOutputs[i]).toString());
        }
    }
}
