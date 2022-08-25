package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void stringToDeadline() {
        String testString = "[D][ ] return book (by: Jun 06 2022)";
        Deadline convertedStr = new Deadline("test", "01-01-1000");
        try {
            convertedStr = Deadline.stringToDeadline(testString);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals(testString, convertedStr.toString());
    }
}