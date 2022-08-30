package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {

    @Test
    public void getDateTest () {
        LocalDate date = LocalDate.parse("2019-10-15");
        Deadline d = new Deadline("Test",date);
        assertEquals("2019-10-15", d.getDate().toString());
    }

    @Test
    public void getDescriptionTest () {
        LocalDate date = LocalDate.parse("2019-10-15");
        Deadline d = new Deadline("Test",date);
        assertEquals("[D]" + "[ ] " + "Test" + "(" + date + ")", d.getDescription());
    }
}
