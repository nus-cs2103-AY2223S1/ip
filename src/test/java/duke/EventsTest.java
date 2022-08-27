package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventsTest {

    @Test
    public void eventsTest(){
        Events event = new Events("carnival ", LocalDateTime.parse("2022-10-02T15:39"));
        assertEquals(event.toString(), "[E][ ] carnival (at: 02 Oct 2022 15:39)");
    }

}
