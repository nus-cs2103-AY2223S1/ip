package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void eventTest() {
        assertEquals(0, new Event("swimming at there", false, "2022-10-03 18:35").getDone());
        assertEquals("swimming at there",
                     new Event("swimming at there", false, "2022-10-03 18:35").getTask());
        assertEquals("2022-10-03T18:35",
                     new Event("swimming at there", false, "2022-10-03 18:35").getOriginalDetail());
        assertEquals("Oct 03 2022 1835",
                     new Event("swimming at there", false, "2022-10-03 18:35").getFormattedDetail());
    }
}
