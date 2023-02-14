package tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void TaskTestOne(){
        Task test = new Event("event", "2002-02-02");
        test.setStatusIcon(true);
        assertEquals(test.toString(), "[E][✧] event AT 02 Feb 2002");
    }

    @Test
    public void TaskTestTwo(){
        Task test = new Deadline("task", "2015-03-01");
        test.setStatusIcon(true);
        assertEquals(test.toString(), "[D][✧] task BY 01 Mar 2015");
    }

    @Test
    public void TaskTestThree(){
        Task test = new Todo("task");
        test.setStatusIcon(false);
        assertEquals(test.toString(), "[T][ ] task");
    }
}