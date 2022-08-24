package Duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    Deadline deadline = new Deadline("running", true, "2022-10-03 18:00");

    @Test
    public void getDoneTest() {
        assertEquals(1, deadline.getDone());
    }
    @Test
    public void getTaskTest() {
        assertEquals("running", deadline.getTask());
    }
    @Test
    public void getOriginalDetailTest() {
        assertEquals("2022-10-03T18:00", deadline.getOriginalDetail());
    }

    @Test
    public void getFormattedDetailTest() {
        assertEquals("Oct 03 2022 1800", deadline.getFormattedDetail());
    }
}
