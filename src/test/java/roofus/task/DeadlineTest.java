package roofus.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void addDeadlineTest() {
        Task deadlineTask = new Deadline("Say hello", "2022-08-23");
        assertEquals("[D][ ] Say hello by: 2022-08-23", deadlineTask.toString());
    }

    @Test
    public void setDoneTest() {
        Task deadlineTask = new Deadline("Say hello", "2022-08-23");
        deadlineTask.setDone();
        assertEquals("[D][X] Say hello by: 2022-08-23", deadlineTask.toString());
        deadlineTask.setDone();
        assertEquals("[D][X] Say hello by: 2022-08-23", deadlineTask.toString());
    }

    @Test
    public void markDeadlineTest() {
        Task deadlineTask = new Deadline("Say hello", "2022-08-23");
        deadlineTask.mark();
        assertEquals("[D][X] Say hello by: 2022-08-23", deadlineTask.toString());
    }

    @Test
    public void unmarkDeadlineTest() {
        Task deadlineTask = new Deadline("Say hello", "2022-08-23");
        deadlineTask.mark();
        deadlineTask.unmark();
        assertEquals("[D][ ] Say hello by: 2022-08-23", deadlineTask.toString());
    }

    @Test
    public void writeDeadlineTest() {
        Task deadlineTask = new Deadline("Say hello", "2022-08-23");
        assertEquals("D | 0 | Say hello | 2022-08-23", deadlineTask.writeString());
        deadlineTask.mark();
        assertEquals("D | 1 | Say hello | 2022-08-23", deadlineTask.writeString());
    }
}
