package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testMarkDeadline(){
        Deadline deadline = new Deadline("laundry","12/12/2022 1500");
        assertEquals("[D][ ] laundry (by: Monday, 12 December 2022)",deadline.toUser());
        deadline.markAsDone();
        assertEquals("[D][X] laundry (by: Monday, 12 December 2022)",deadline.toUser());
    }

    @Test
    public void testUnmarkDeadline(){
        Deadline deadline = new Deadline("laundry","12/12/2022 1500");
        assertEquals("[D][ ] laundry (by: Monday, 12 December 2022)",deadline.toUser());
        deadline.markAsDone();
        assertEquals("[D][X] laundry (by: Monday, 12 December 2022)",deadline.toUser());
        deadline.markAsUndone();
        assertEquals("[D][ ] laundry (by: Monday, 12 December 2022)",deadline.toUser());

    }
}