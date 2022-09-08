package duke;

import duke.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void unmarkedTest(){
        assertEquals(new Event("123456", "2pm").toString(),
                "[E][ ] 123456 (at: 2pm)");
    }

    @Test
    public void markedTest(){
        Event temp = new Event("123456", "2pm");
        temp.markAsDone();
        assertEquals(temp.toString(), "[E][X] 123456 (at: 2pm)");
    }
}