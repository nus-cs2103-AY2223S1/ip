package Duke;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testMarkForDeadline(){
        Task deadline = new Deadline("sleep","12-12-2022");
        assertEquals("[D][ ] sleep (by: Dec 12 2022)",deadline.toString());
        deadline.markAsDone();
        assertEquals("[D][x] sleep (by: Dec 12 2022)",deadline.toString());
    }

    @Test
    public void testUnmarkForDeadline(){
        Task deadline = new Deadline("sleep","12-12-2022");
        assertEquals("[D][ ] sleep (by: Dec 12 2022)",deadline.toString());
        deadline.markAsDone();
        assertEquals("[D][x] sleep (by: Dec 12 2022)",deadline.toString());
        deadline.UnmarkAsDone();
        assertEquals("[D][ ] sleep (by: Dec 12 2022)",deadline.toString());
    }

}
