package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void dbRepresentationFromUserInput() {
        Task deadline = new Deadline("assignment", "24/04/2019 1600");
        assertEquals("D|false|assignment|2019-04-24T16:00", deadline.dbRepresentation());
    }

    @Test
    public void dbRepresentationFromDbRead() {
        Task deadline = new Deadline(false, "assignment", "2019-04-24T16:00");
        assertEquals("[D][ ] assignment(by: 24/4/2019 1600)", deadline.toString());
    }
}
