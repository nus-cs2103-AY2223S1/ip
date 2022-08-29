package gibson.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void taskList_emptyList_success() {
        TaskList tl = new TaskList(new ArrayList<String>());
        assertEquals("", tl.toString());
    }

    @Test
    public void taskList_populatedList_success() {
        ArrayList<String> al = new ArrayList<String>();
        al.add("1.[T][ ] Test");
        al.add("2.[D][ ] Test2 (by: 1 Aug 2022 23:59)");
        al.add("3.[E][ ] Test3 (at: 1 August)");
        TaskList tl = new TaskList(al);
        assertEquals("1.[T][ ] Test\n"
                + "2.[D][ ] Test2 (by: 1 Aug 2022 23:59)\n"
                + "3.[E][ ] Test3 (at: 1 August)", tl.toString());
    }
}
