package duke;

import duke.events.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {

    @Test
    public void deadlinesSaveData(){
        LocalDateTime date = LocalDateTime.parse("2020-01-01T23:59:59");
        Deadline d = new Deadline("Drink milk", date);
        String expected = " D | false | Drink milk | 2020-01-01T23:59:59";
        String actual = d.getSaveData();
        assertEquals(expected, actual);
    }

    @Test
    public void deadlinesToString(){
        LocalDateTime date = LocalDateTime.parse("2020-01-01T23:59:59");
        Deadline d = new Deadline("Drink milk", date);
        String expected = "[D] [ ] Drink milk (by: 11:59 PM on 01/01/2020)";
        String actual = d.toString();
        assertEquals(expected, actual);
    }

}
