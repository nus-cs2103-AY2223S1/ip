package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {

    @Test
    public void deadlinesTest(){
        Deadlines deadline = new Deadlines("buy food ", LocalDateTime.parse("2022-10-02T16:00"));
        assertEquals(deadline.toString(), "[D][ ] buy food  (by: 02 Oct 2022 16:00)");
    }

}
