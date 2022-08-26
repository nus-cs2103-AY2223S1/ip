
package duke.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTest {
    protected DateTimeFormatter datetime_format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void deadlineInitTest() throws DukeException {
        String des = "Testing...";
        String datetime = "2020-01-02 03:04";
        LocalDateTime dt = LocalDateTime.parse(datetime, datetime_format);
        Deadline td = new Deadline(des, dt);
        String cur = td.toString();
        String target = "[D][ ] Testing... < 03:04 Thursday 02 January 2020 >";
        assertEquals(cur, target);
    }

}