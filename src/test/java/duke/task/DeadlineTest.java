package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class DeadlineTest {
    @Test
    public void getByTest() {
        Deadline sampleDeadline = new Deadline("Celebrate birthday", "2023-03-28");
        assertEquals(LocalDate.parse("2023-03-28"), sampleDeadline.getBy());
    }

    @Test
    public void toStringTest() {
        Deadline sampleDeadline = new Deadline("Celebrate birthday", "2023-03-28");
        assertEquals("[D][ ] Celebrate birthday (by: Mar 28 2023)", sampleDeadline.toString());
    }
}