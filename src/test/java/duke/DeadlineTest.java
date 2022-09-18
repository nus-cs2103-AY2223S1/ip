package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void test(){
        Deadline dl = new Deadline("eat and sleep", LocalDateTime.of(2022, 8, 30, 15, 0, 0));
        assertEquals("[D][ ] eat and sleep (by: 30 Aug 2022 15:00)", dl.toString());
    }
}