package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test

    public void toStringTest() {

        Event temp1 = new Event("", false, "24 Aug 3-9pm", PriorityLevel.Priority.MEDIUM);

        assertEquals("[E][ ]  (at: 24 Aug 3-9pm) (Priority: MEDIUM)", temp1.toString());

        Event temp2 = new Event("play valorant", false, "24 Aug 3-9pm", PriorityLevel.Priority.MEDIUM);

        assertEquals("[E][ ] play valorant (at: 24 Aug 3-9pm) (Priority: MEDIUM)", temp2.toString());

        temp2.mark();

        assertEquals("[E][X] play valorant (at: 24 Aug 3-9pm) (Priority: MEDIUM)", temp2.toString());
    }

}
