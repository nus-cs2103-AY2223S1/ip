package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void todoToStringTest(){
        Task todoTestTask = new Todo("testing todo");
        assertEquals("[T][ ]testing todo", todoTestTask.toString());
    }

    @Test
    public void deadlineToStringTest(){
        Task deadlineTestTask = new Deadline("testing deadline", LocalDate.parse("2022-08-25"));
        assertEquals("[D][ ]testing deadline (by: AUGUST 25 2022)", deadlineTestTask.toString());
    }

    @Test
    public void eventToStringTest(){
        Task eventTestTask = new Event("testing event", "test date");
        assertEquals("[E][ ]testing event (at: test date)", eventTestTask.toString());
    }
}
