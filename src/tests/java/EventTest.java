
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testDeadline(){
        assertEquals("[E][] return brush (at: Oct 15 2019)", new Event("return brush","2019-10-15").toString());
    }

}