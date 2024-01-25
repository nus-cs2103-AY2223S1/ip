package cs2103t.ip.duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void addDeadlineTest() {
        Deadlines deadline = new Deadlines("Read Textbook", LocalDate.parse("2022-09-01"));
        assertEquals(deadline.addString(4), "_______________________________\n" +
                "Got it. I've added this task: \n" +
                "[D][ ] Read Textbook (by: Sep 1 2022)\n" +
                "Now you have 4 tasks in the list \n" +
                "_______________________________\n");
    }
}
