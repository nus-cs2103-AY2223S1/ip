package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskCreatorTest {

    @Test
    public void createTodoTest() {
        assertEquals(TaskCreator.createTask("todo read book").toString(),
                "[T][ ] read book");
    }

    @Test
    public void createDeadlineTest() {
        assertEquals(TaskCreator.createTask("deadline return book /by 2022-5-12 1800").toString(),
                "[D][ ] return book (by: May 12 2022 6:00 PM)");
    }

    @Test
    public void createEventTest() {
        assertEquals(TaskCreator.createTask("event book fest /at Sunday 2-4pm").toString(),
                "[E][ ] book fest (at: Sunday 2-4pm)");
    }

    @Test
    public void createTodoFromStorage() {
        assertEquals(TaskCreator.createFromStorage("[T]--[X]--read book").toString(),
                "[T][X] read book");
    }

    @Test
    public void createDeadlineFromStorage() {
        assertEquals(TaskCreator.createFromStorage("[D]--[X]--return book--Mar 4 2022 6:00 PM").toString(),
                "[D][X] return book (by: Mar 4 2022 6:00 PM)");
    }

    @Test
    public void createEventFromStorage() {
        assertEquals(TaskCreator.createFromStorage("[E]--[X]--festival--Today 6pm").toString(),
                "[E][X] festival (at: Today 6pm)");
    }
}
