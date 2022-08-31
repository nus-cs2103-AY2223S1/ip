package duke.task;
import duke.DukeException;
import duke.task.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void eventTest1(){
        try {
            Event event = new Event("sister's grad", "2023-01-10 1700");
            assertEquals("[E][ ] sister's grad (at: 10 Jan 2023 05:00PM)", event.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void eventTest2(){
        try {
            Event event = new Event("housewarming", "2022-12-10");
            fail();
        } catch (DukeException e) {
            assertEquals("Input your date and time in the format yyyy-MM-dd HHmm!"
                    , e.getMessage());
        }
    }

    @Test
    public void eventTest3(){
        try {
            Event event = new Event("housewarming", "2022-12-25 1800");
            assertEquals("[E][ ] housewarming (at: 25 Dec 2022 06:00PM)", event.toString());
        } catch (DukeException e) {
            fail();
        }
    }
}
