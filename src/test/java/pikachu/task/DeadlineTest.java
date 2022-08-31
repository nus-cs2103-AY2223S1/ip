package pikachu.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void getNameTest(){
        assertEquals("D", new Deadline("", LocalDate.now()).getName());
    }

    @Test
    public void getTimingTest(){
        assertEquals("6 July 2022", new Deadline("",LocalDate.of(2022,7,6)).getTiming());
    }
}
