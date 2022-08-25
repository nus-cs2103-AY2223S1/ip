package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toCsv_deadline_taskCsvReturned() {
        Deadline deadlineTest = new Deadline("write an essay", "2022-07-06");
        String expected = "D,0,write an essay,2022-07-06\n";
        assertEquals(expected, deadlineTest.toCsv());
    }
}
