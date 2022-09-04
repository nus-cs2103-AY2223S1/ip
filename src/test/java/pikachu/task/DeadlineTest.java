package pikachu.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void getName_rightName(){
        assertEquals("D", new Deadline("", LocalDate.now()).getName());
    }

    @Test
    public void getTiming_rightTiming(){
        assertEquals("6 July 2022", new Deadline("",LocalDate.of(2022,7,6)).getTiming());
    }
}
