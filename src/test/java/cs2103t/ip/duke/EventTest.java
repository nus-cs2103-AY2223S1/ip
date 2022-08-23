package cs2103t.ip.duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void addEventTest() {
        Event event = new Event("Read Textbook", LocalDate.parse("2022-09-01"));
        assertEquals(event.addString(3), "_______________________________\n" +
                "Got it. I've added this task: \n" +
                "[E][ ] Read Textbook (at: Sep 1 2022)\n" +
                "Now you have 3 tasks in the list \n" +
                "_______________________________\n");
    }
}
