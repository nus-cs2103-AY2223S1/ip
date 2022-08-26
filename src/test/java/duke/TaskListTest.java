package duke;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private Todo todo = new Todo("Borrow Book");
    private Deadline dl = new Deadline("Borrow Book", LocalDate.parse("2022-02-02"));
    public TaskList tl = new TaskList();

    @Test
    public void countTest(){
        tl.add(todo);
        tl.add(dl);
        assertEquals(2, tl.count());
    }
}
