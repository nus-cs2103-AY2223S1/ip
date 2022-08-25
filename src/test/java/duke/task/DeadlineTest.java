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

    @Test
    public void markDeadline() {
        Deadline deadline = new Deadline("homework", "2009-09-09");
        deadline.markAsDone();
        assertEquals("[D][X] homework (by: Sep 09 2009)", deadline.toString());
    }

    @Test
    public void setStatus_notDone_markDone() {
        Deadline deadline = new Deadline("homework", "2009-09-09");
        deadline.setIsDone(true);
        assertEquals("[D][X] homework (by: Sep 09 2009)", deadline.toString());
    }
}
