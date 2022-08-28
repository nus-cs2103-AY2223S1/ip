package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test

    public void toStringTest() {

        Event temp1 = new Event("", false, "24 Aug 3-9pm");

        assertEquals("[E][ ]  (at: 24 Aug 3-9pm)", temp1.toString());

        Event temp2 = new Event("play valorant", false,"24 Aug 3-9pm");

        assertEquals("[E][ ] play valorant (at: 24 Aug 3-9pm)", temp2.toString());

        temp2.mark();

        assertEquals("[E][X] play valorant (at: 24 Aug 3-9pm)", temp2.toString());
    }


}
