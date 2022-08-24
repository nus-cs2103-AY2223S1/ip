package Duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Event event = new Event("swimming at there", false, "2022-10-03 18:35");

    @Test
    public void getDoneTest() {
        assertEquals(0, event.getDone());
    }
    @Test
    public void getTaskTest() {
        assertEquals("swimming at there", event.getTask());
    }
    @Test
    public void getOriginalDetailTest() {
        assertEquals("2022-10-03T18:35", event.getOriginalDetail());
    }

    @Test
    public void getFormattedDetailTest() {
        assertEquals("Oct 03 2022 1835", event.getFormattedDetail());
    }
}
