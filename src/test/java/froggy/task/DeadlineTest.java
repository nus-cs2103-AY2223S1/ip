package seedu.duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DeadlineTest {
    @Test
    public void deadlineConstructorNoIsDone_normalDeadlineWithDeadlineDateTime_toStringCorrect() {
        Deadline deadline = new Deadline("return book", "2019-10-15");
        System.out.println(deadline);
        assertEquals(deadline.toString(), "[D][ ] return book (by: Oct 15 2019)");
    }

    @Test
    public void deadlineConstructorIsDone_normalDeadlineWithDeadlineDateTime_toStringCorrect() {
        Deadline deadline = new Deadline("return book", "2019-10-15", true);
        System.out.println(deadline);
        assertEquals(deadline.toString(), "[D][X] return book (by: Oct 15 2019)");
    }
}