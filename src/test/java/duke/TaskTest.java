package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    Task testTaskOne = new Task("This is a test task");
    Task testTaskTwo = new Task("This is also a test task");

    @Test
    public void statusIconTest(){
        assertEquals(" ", testTaskOne.getStatusIcon());
        testTaskTwo.setIsDone(true);
        assertEquals("X",testTaskTwo.getStatusIcon());
    }

    @Test
    public void isDoneTest(){
        assertEquals(false, testTaskOne.getIsDone());
        testTaskTwo.setIsDone(true);
        assertEquals(true,testTaskTwo.getIsDone());
    }

}
