package sus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import sus.task.Deadline;

public class DeadlineTest {

    @Test
    public void newDeadlineTest() {
        Deadline deadline = new Deadline("This is a test description", LocalDate.parse("2000-02-02"));
        assertEquals(deadline.toString(), "[D][ ] This is a test description (by: February 2, 2000)");
    }

    @Test
    public void markDeadlineTest() {
        Deadline deadline = new Deadline("This is a test description", LocalDate.parse("2000-02-02"));
        deadline.setDone(true);
        assertEquals(deadline.toString(), "[D][X] This is a test description (by: February 2, 2000)");
    }
}
