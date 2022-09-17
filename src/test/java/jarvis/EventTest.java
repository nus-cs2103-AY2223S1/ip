package jarvis;

import jarvis.task.Event;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Event test = new Event("gym", "2022-09-08");

    @Test
    public void toString_normalInput_displayCorrectly(){
        assertEquals("[E][ ] gym (at: Sep 8 2022)" ,test.toString());
    }

    @Test
    public void mark_normalInput_markedCorrectly(){
        test.mark();
        assertEquals("[E][X] gym (at: Sep 8 2022)", test.toString());
    }

    @Test
    public void unmark_normalInput_unmarkedCorrectly(){
        test.unmark();
        assertEquals("[E][ ] gym (at: Sep 8 2022)", test.toString());
    }
}