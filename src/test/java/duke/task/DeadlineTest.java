package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void getDateTest() {
        LocalDate date = LocalDate.parse("2019-10-15");
        Deadline d = new Deadline("Test", date, 'X');
        assertEquals("2019-10-15", d.getDate().toString());
    }

    @Test
    public void getDescriptionTest() {
        LocalDate date = LocalDate.parse("2019-10-15");
        Deadline d = new Deadline("Test", date, 'X');
        assertEquals("[D]" + "[X] " + "Test" + "(" + date + ")", d.getDescription());
    }
}
