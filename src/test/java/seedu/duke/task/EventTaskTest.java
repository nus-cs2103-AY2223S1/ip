package seedu.duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTaskTest {

    @Test
    public void dateTest() {
        String date1 = "2022-08-11";
        EventTask testEvent1 = new EventTask("Test Event 1", date1);
        String date2 = "August 11";
        EventTask testEvent2 = new EventTask("Test Event 2", date2);

        assertEquals("[E][ ] Test Event 1 (on: Aug 11 2022)", testEvent1.toString());
        assertEquals("[E][ ] Test Event 2 (on: August 11)", testEvent2.toString());

        testEvent1.editTime("August 11");
        testEvent1.changeDesc("Test Event 3");
        testEvent2.editTime("2022-08-11");
        testEvent2.changeDesc("Test Event 4");
        assertEquals("[E][ ] Test Event 3 (on: August 11)", testEvent1.toString());
        assertEquals("[E][ ] Test Event 4 (on: Aug 11 2022)", testEvent2.toString());
    }
}
