package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    LocalDate date = LocalDate.parse("2019-12-01");
    String timeData = "1800";
    LocalTime time = LocalTime.of(Integer.parseInt(timeData.substring(0,2)),
            Integer.parseInt(timeData.substring(2)));
    Deadline deadline = new Deadline("return book", date , time);

    @Test
    public void test_Deadline_toString() {
        assertEquals("[D][ ] return book (by: Dec 01 2019 06:00 PM)", deadline.toString());
    }

    @Test
    public void test_Deadline_parse() {
        assertEquals("D#0#return book#Dec 01 2019#06:00 PM", deadline.parse());
    }



}
