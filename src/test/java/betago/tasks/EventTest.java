package betago.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void newEventTest(){
        assertEquals("[E][ ] attend this concert (at: NUS)"
                , new Event("attend this concert", "NUS").toString());
    }
}
