import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDeadline(){
        assertEquals("[D][] return brush (by: Oct 15 2019)", new Deadline("return brush","2019-10-15").toString());
    }

}