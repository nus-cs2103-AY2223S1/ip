package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    Deadline testEvent = new Deadline("lunch", "2022-02-03", "12:00");
    @Test
    public void deadlineToString_correctStringFormat() {
        assertEquals(testEvent.toString(), "[D][ ] lunch (by: Feb 3 2022, 1200PM)");
    }

    @Test
    public void eventToFile_correctFileFormat(){
        assertEquals(testEvent.toFile(), "D|0|lunch|2022-02-03|12:00\n");
    }
}