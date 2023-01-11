import duke.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        Deadline task = new Deadline("Create Task ");
        task.setDate(LocalDate.parse("2019-12-02"));
        task.setTime("1800");
        assertEquals("[D] [ ] Create Task (by: Dec 02 2019, 6:00pm)", task.toString());
    }

    @Test
    public void testGetDateFormat() {
        Deadline task = new Deadline("Create Task ");
        task.setDate(LocalDate.parse("2019-12-02"));
        assertEquals("2019-12-02", task.getDateFormat());
    }

    @Test
    public void testGetTime() {
        Deadline task = new Deadline("Create task ");
        task.setTime("0030");
        assertEquals("12:30am", task.getTime());
    }

}
