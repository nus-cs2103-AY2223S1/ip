package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;



public class DeadlineTest {
    private LocalDate date = LocalDate.parse("2019-12-01");
    private String timeData = "1800";
    private LocalTime time = LocalTime.of(Integer.parseInt(timeData.substring(0, 2)),
            Integer.parseInt(timeData.substring(2)));
    private Deadline deadline = new Deadline("return book", date , time);

    @Test
    public void test_deadline_toString() {
        assertEquals("[D][ ] return book (by: Dec 01 2019 06:00 PM)", deadline.toString());
    }

    @Test
    public void test_deadline_parse() {
        assertEquals("D#0#return book#Dec 01 2019#06:00 PM", deadline.parse());
    }

}
