package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void getTaskTypeTest(){
        assertEquals(new Event("read book", "2019-10-15").getTaskType(), "E");
    }

    @Test
    public void toStringTest(){
        assertEquals(new Event("read book", "2019-10-15").toString(),
                "[E][ ] read book (at: Oct 15 2019)" );
    }
}
