package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


public class DeadlineTest {

    @Test
    public void toStringTest() throws DukeException {
        LocalDate by = LocalDate.parse("2019-12-02");
        Deadline newDeadline = new Deadline("Deadline", by, "1800");
        assertEquals("[D][] Deadline (by: 2019-12-02 1800)", newDeadline.toString());
    }

    @Test
    public void anotherToStringTest() throws DukeException {
        LocalDate by = LocalDate.parse("2022-08-15");
        Deadline newDeadline = new Deadline("Deadline", by, "2300");
        assertEquals("[D][] Deadline (by: 2022-08-15 2300)", newDeadline.toString());
    }

}
