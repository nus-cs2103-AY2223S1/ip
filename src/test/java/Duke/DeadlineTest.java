package Duke;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testMarkForDeadline(){
        Task deadline = new Deadline("sleep","12-12-2022");
        assertEquals(deadline.toString(), "[D][ ] sleep (by: Dec 12 2022)");
        deadline.markAsDone();
        assertEquals(deadline.toString(), "[D][x] sleep (by: Dec 12 2022)");
    }

    @Test
    public void testUnmarkForDeadline(){
        Task deadline = new Deadline("sleep","12-12-2022");
        assertEquals(deadline.toString(), "[D][ ] sleep (by: Dec 12 2022)");
        deadline.markAsDone();
        assertEquals(deadline.toString(), "[D][x] sleep (by: Dec 12 2022)");
        deadline.UnmarkAsDone();
        assertEquals(deadline.toString(), "[D][ ] sleep (by: Dec 12 2022)");
    }

}
