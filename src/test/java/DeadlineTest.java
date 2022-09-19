import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.Deadline;

import java.time.LocalDate;


public class DeadlineTest {
    @Test
    public void markDeadlineTest(){
        Deadline deadline = new Deadline("buy bread", LocalDate.parse("2000-05-07"));
        deadline.markAsDone();
        assertEquals("[D][X]buy bread (by: May 7 2000)", deadline.toString());
    }

    @Test
    public void unmarkTodoTest(){
        Deadline deadline = new Deadline("buy bread", LocalDate.parse("2000-05-07"));
        deadline.markAsDone();
        deadline.markAsUndone();
        assertEquals("[D][ ]buy bread (by: May 7 2000)", deadline.toString());
    }
}
