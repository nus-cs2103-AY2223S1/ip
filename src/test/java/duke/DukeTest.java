package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void getIndexInTaskListTest() {
        int expectedOutput = Duke.getIndexInTaskList("delete 4");
        int actualOutput = 3;
        assertEquals(expectedOutput, actualOutput);
    }
}
