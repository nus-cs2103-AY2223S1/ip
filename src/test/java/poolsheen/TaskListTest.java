package poolsheen;

import org.junit.jupiter.api.Test;

import poolsheen.task.Task;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    public class ToDoStub extends Task {
        public ToDoStub() {
            super("desc", false);
        }

        //Code should not use this function
        @Override
        public String[] toArr() {
            return new String[]{"Code should not reach here"};
        }
    }

    @Test
    public void size_emptyTaskList_success() {
        TaskList tl = new TaskList(new ArrayList<Task>(100));
        assertEquals(0, tl.size());
    }

    @Test
    public void size_twoSizeTaskList_success() {
        TaskList tl = new TaskList(new ArrayList<Task>(100));
        tl.add(new ToDoStub());
        tl.add(new ToDoStub());
        assertEquals(2, tl.size());
    }

    @Test
    public void mark_oneSizeTaskList_success() {
        TaskList tl = new TaskList(new ArrayList<Task>(100));
        tl.add(new ToDoStub());
        tl.mark(1);
        assertEquals("X", tl.get(0).getStatusIcon());
    }

    @Test
    public void unmark_oneSizeTaskList_success() {
        TaskList tl = new TaskList(new ArrayList<Task>(100));
        tl.add(new ToDoStub());
        tl.unmark(1);
        assertEquals("-", tl.get(0).getStatusIcon());
    }

    @Test
    public void deleteTask_oneSizeTaskList_success() {
        TaskList tl = new TaskList(new ArrayList<Task>(100));
        tl.add(new ToDoStub());
        tl.deleteTask(1);
        assertEquals(0, tl.size());
    }
}
