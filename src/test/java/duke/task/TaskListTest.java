package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void test() {
        ArrayList<Task> arrayList = new ArrayList<>();
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getSize());
        assertEquals(arrayList, taskList.getTaskList());
        try {
            Task t1;
            Task t2;
            Task t3;
            t1 = new Todo("return book");
            taskList.addTask(t1);
            t2 = new Deadline("finish Duke project", LocalDate.parse("2022-08-25"));
            taskList.addTask(t2);
            t3 = new Event("meet project teammates", LocalDate.parse("2022-08-27"));
            taskList.addTask(t3);
            assertEquals(3, taskList.getSize());
        } catch (Exception e) {
            fail();
        }
    }
}
