package seedu.duke;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {
    @Test
    public void Deadline_wrongDateFormat_exceptionThrown(){
        assertThrows(DateTimeParseException.class,
                () -> {new Deadline("write testcase", "now");});
    }

    @Test
    public void toStore_correctInputs_correctStringReturned(){
        assertEquals("D : 0 : finish iP : 05 May 2022 11:00 PM",
                new Deadline("finish iP", "2022-05-05 11:00 PM").toStore());
    }
}

