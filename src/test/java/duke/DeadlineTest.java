package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void deadlineTest() {
        assertEquals(1, new Deadline("running", true, "2022-10-03 18:00").getDone());
        assertEquals("running", new Deadline("running", true, "2022-10-03 18:00").getTask());
        assertEquals("2022-10-03T18:00", new Deadline("running", true, "2022-10-03 18:00")
                                                              .getOriginalDetail());
        assertEquals("Oct 03 2022 1800", new Deadline("running", true, "2022-10-03 18:00")
                                                              .getFormattedDetail());
    }
}
