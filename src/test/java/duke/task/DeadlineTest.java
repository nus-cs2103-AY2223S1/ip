package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void deadlineTest() {
        Deadline d = new Deadline("something", LocalDate.parse("2020-01-01"));
        assertEquals(d.toString(), "[D] [ ] something (by: Jan 1 2020)");
    }

    @Test
    public void deadlineTest2() {
        Deadline d = new Deadline("something", LocalDate.parse("2020-01-01"));
        d.mark();
        assertEquals(d.toString(), "[D] [x] something (by: Jan 1 2020)");
    }

    @Test
    public void deadlineTest3() {
        Deadline d = new Deadline("something", LocalDate.parse("2020-01-01"));
        d.mark();
        d.unmark();
        assertEquals(d.toString(), "[D] [ ] something (by: Jan 1 2020)");
    }
}
