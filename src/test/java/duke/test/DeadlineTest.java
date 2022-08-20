package duke.test;

import duke.task.Deadline;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest(){
        LocalDate d1 = LocalDate.parse("2022-12-08");
        Deadline deadline = new Deadline("submit assignment", d1);
        assertEquals("[D][ ] submit assignment (by: Dec 8 2022)", deadline.toString());
    }
}