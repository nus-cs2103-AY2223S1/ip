package seedu.duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void Test1(){
        TaskList taskL = new TaskList();
        taskL.add(new Task("Dinner Party", "event", LocalDate.parse("2013-12-23")));
        assertEquals(taskL.mark(1).toString(), "[E][X] Dinner Party(at Dec 23 2013)");
    }

    @Test
    public void Test2(){
        TaskList taskL = new TaskList();
        taskL.add(new Task("Dinner Party", "event", LocalDate.parse("2013-12-23")));
        taskL.mark(1);
        assertEquals(taskL.unmark(1).toString(), "[E][ ] Dinner Party(at Dec 23 2013)");
    }

    @Test
    public void Test3(){
        TaskList taskL = new TaskList();
        taskL.add(new Task("Dinner Party", "event", LocalDate.parse("2013-12-23")));
        assertEquals(taskL.get(1).toString(), "[E][ ] Dinner Party(at Dec 23 2013)");
    }

    @Test
    public void Test4(){
        TaskList taskL = new TaskList();
        taskL.add(new Task("Dinner Party", "event", LocalDate.parse("2013-12-23")));
        taskL.delete(1);
        assertEquals(taskL.size(), 0);
    }
}
