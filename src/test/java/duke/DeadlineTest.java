package duke;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;


class DeadlineTest {

    @Test
    void getDateTimeTest() {
        LocalDateTime today = LocalDateTime.now();
        String todayString = " (by: " + today.format(DateTimeFormatter.ofPattern("MMM dd yyyy', ' hh:mm a")) + ")";
        Deadline newDeadline1 = new Deadline(today, "CS2103");
        Deadline newDeadline2 = new Deadline(today, "PHS3122", true);
        assertAll(() -> assertEquals(newDeadline1.getDateTime(), todayString), () ->
                assertEquals(newDeadline2.getDateTime(), todayString)
        );
    }

    @Test
    void getRawDateTimeTest() {
        LocalDateTime today = LocalDateTime.now();
        Deadline newDeadline1 = new Deadline(today, "CS2103");
        Deadline newDeadline2 = new Deadline(today, "PHS3122", false);
        assertAll(() -> assertEquals(newDeadline1.getRawDateTime(), today), () ->
                assertEquals(newDeadline2.getRawDateTime(), today)
        );
    }

    @Test
    void printTextTest() {
        LocalDateTime today = LocalDateTime.now();
        Deadline newDeadline1 = new Deadline(today, "CS2103");
        Deadline newDeadline2 = new Deadline(today, "PHS3122", true);
        String printDeadline1 = "D |   | CS2103 | " + newDeadline1.getDateTime();
        String printDeadline2 = "D | X | PHS3122 | " + newDeadline2.getDateTime();
        assertAll(() -> assertEquals(printDeadline1, newDeadline1.printText()), () ->
                assertEquals(printDeadline2, newDeadline2.printText())
        );
    }

    @Test
    void testToString() {
        LocalDateTime today = LocalDateTime.now();
        Deadline newDeadline = new Deadline(today, "PHS3122", true);
        String toString = "[D][X] PHS3122" + newDeadline.getDateTime();
        assertEquals(toString, newDeadline.toString());
    }
}