package dukechatbot.utility;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The EventTest class is a test class to test the correctness of
 * the Event class.
 */
public class EventTest {
    /**
     * Tests the toString of the Event class.
     */
    @Test
    public void eventToStringTest() {
        if ((new Event("project", "2022-08-08 04:30 05:30").toString())
                .equals("[E][ ] project (at: 2022-08-08 04:30 to 2022-08-08 05:30)")) {
            assertEquals(1,1);
        } else {
            assertEquals(1,0);
        }
    }
}
