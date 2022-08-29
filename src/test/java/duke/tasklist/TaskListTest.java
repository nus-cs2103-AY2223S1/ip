package duke.tasklist;
import duke.listobjects.Event;
import duke.listobjects.ListObject;
import duke.listobjects.ToDo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest{

    @Test
    public void testItemAddition(){
        ListObject l1 = new ToDo("Test this", 1);
        ListObject l2 = new Event("Am writing test cases", 0);
        ListObject l3 = new Event("Finish test cases", 1);

        TaskList list = new TaskList();

        assertEquals("0 tasks ", list.knowTaskCount());
        list.handleItemAddition(l1);
        assertEquals("1 tasks ", list.knowTaskCount());
        list.handleItemAddition(l2);
        assertEquals("2 tasks ", list.knowTaskCount());
        list.handleItemAddition(l3);
        assertEquals("3 tasks ", list.knowTaskCount());

    }

    @Test
    public void testItemDeletion(){
        ListObject l1 = new ToDo("Test this", 1);
        ListObject l2 = new ToDo("Am writing test cases", 0);
        ListObject l3 = new ToDo("Finish test cases", 1);
        TaskList list = new TaskList();
        list.handleItemAddition(l1);
        list.handleItemAddition(l2);
        list.handleItemAddition(l3);
        assertEquals("3 tasks ", list.knowTaskCount());
        list.handleItem("DELETE", 2);
        assertEquals("2 tasks ", list.knowTaskCount());
        list.handleItem("DELETE", 1);
        list.handleItem("DELETE", 0);
        assertEquals("0 tasks ", list.knowTaskCount());

    }

    @Test
    public void testMarkingTasks(){
        ListObject l1 = new ToDo("Test this", 1);
        TaskList list = new TaskList();
        list.handleItemAddition(l1);
        assertEquals("0. [T][X] Test this", list.toString());
        list.handleItem("UNMARK", 0);
        assertEquals("0. [T][ ] Test this", list.toString());
        list.handleItem("MARK", 0);
        assertEquals("0. [T][X] Test this", list.toString());
    }
}