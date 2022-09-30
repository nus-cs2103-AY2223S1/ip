package duke.tasklist;

import duke.exceptions.InvalidItemException;
import duke.listobjects.Event;
import duke.listobjects.ListObject;
import duke.listobjects.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest{

    @Test
    public void testValidItemAddition() {
        ListObject l1 = new ToDo("Test this", 1);
        ListObject l2 = new Event("Am writing test cases",  0, "2022-10-10 18:00 19:00");
        ListObject l3 = new Event("Finish test cases", 1, "2022-10-10 18:00 19:00");

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
    public void testValidItemDeletion(){
        ListObject l1 = new ToDo("Test this", 1);
        ListObject l2 = new ToDo("Am writing test cases", 0);
        ListObject l3 = new ToDo("Finish test cases", 1);
        TaskList list = new TaskList();
        list.handleItemAddition(l1);
        list.handleItemAddition(l2);
        list.handleItemAddition(l3);
        assertEquals("3 tasks ", list.knowTaskCount());
        try {
            list.handleItem("DELETE", 2);
            assertEquals("2 tasks ", list.knowTaskCount());
            list.handleItem("DELETE", 1);
            list.handleItem("DELETE", 0);
            assertEquals("0 tasks ", list.knowTaskCount());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testInvalidItemDeletion(){
        ListObject l1 = new ToDo("Test this", 1);
        ListObject l2 = new ToDo("Am writing test cases", 0);
        ListObject l3 = new ToDo("Finish test cases", 1);
        TaskList list = new TaskList();
        list.handleItemAddition(l1);
        list.handleItemAddition(l2);
        list.handleItemAddition(l3);
        assertEquals("3 tasks ", list.knowTaskCount());
        try {
            list.handleItem("DELETE", 4);
        } catch (Exception e){
            assertEquals(new InvalidItemException().getMessage(), e.getMessage());
        }

    }

    @Test
    public void testValidMarkingTasks(){
        try {
            ListObject l1 = new ToDo("Test this", 1);
            TaskList list = new TaskList();
            list.handleItemAddition(l1);
            assertEquals("1. [T][X] Test this\n", list.toString());
            list.handleItem("UNMARK", 1);
            assertEquals("1. [T][ ] Test this\n", list.toString());
            list.handleItem("MARK", 1);
            assertEquals("1. [T][X] Test this\n", list.toString());

        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Test
    public void testInvalidMarkingTasks(){
        try{
            ListObject l1 = new ToDo("Test this", 1);
            TaskList list = new TaskList();
            list.handleItemAddition(l1);
            assertEquals("1. [T][X] Test this\n", list.toString());
            list.handleItem("UNMARK", 0);
            assertEquals("1. [T][ ] Test this\n", list.toString());
            list.handleItem("MARK", 2);

        } catch (Exception e) {
            assertEquals(new InvalidItemException().getMessage(), e.getMessage());
        }
    }
}