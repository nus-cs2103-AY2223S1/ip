package duke.task;
import duke.Duke;
import duke.common.DukeException;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void test() {
        TaskList taskList = new TaskList();
        ArrayList<Task> arrayList = new ArrayList<Task>();

        assertEquals(0, taskList.size());
        assertEquals(arrayList, taskList.getTasks());
        assertEquals("", taskList.toString());

        try {
            Task task = new ToDo("todo", false);
            taskList.addItem(task);
            arrayList.add(task);
        } catch (Exception exception) {
            fail();
        }

        assertEquals(1, taskList.size());
        assertEquals(arrayList, taskList.getTasks());
        assertEquals("1. [T][ ] todo\n", taskList.toString());

        try {
            Task task = new Deadline("deadline", true, LocalDate.parse("2022-12-12"));
            taskList.addItem(task);
            arrayList.add(task);
        } catch (Exception exception) {
            fail();
        }

        assertEquals(2, taskList.size());
        assertEquals(arrayList, taskList.getTasks());
        assertEquals("1. [T][ ] todo\n2. [D][X] deadline (by: Dec 12, 2022)\n", taskList.toString());

        try {
            Task task = new Event("event", true, LocalDate.parse("2012-12-12"));
            taskList.addItem(task);
            arrayList.add(task);
        } catch (Exception exception) {
            fail();
        }

        assertEquals(3, taskList.size());
        assertEquals(arrayList, taskList.getTasks());
        assertEquals("1. [T][ ] todo\n2. [D][X] deadline (by: Dec 12, 2022)\n3. [E][X] event (at: Dec 12, 2012)\n", taskList.toString());

        try {
            taskList.markItem(0);
        } catch (Exception exception) {
            fail();
        }

        assertEquals(3, taskList.size());
        assertEquals(arrayList, taskList.getTasks());
        assertEquals("1. [T][X] todo\n2. [D][X] deadline (by: Dec 12, 2022)\n3. [E][X] event (at: Dec 12, 2012)\n", taskList.toString());

        try {
            taskList.unMarkItem(2);
        } catch (Exception exception) {
            fail();
        }

        assertEquals(3, taskList.size());
        assertEquals(arrayList, taskList.getTasks());
        assertEquals("1. [T][X] todo\n2. [D][X] deadline (by: Dec 12, 2022)\n3. [E][ ] event (at: Dec 12, 2012)\n", taskList.toString());

        assertEquals("1. [T][X] todo\n2. [D][X] deadline (by: Dec 12, 2022)\n", taskList.filter(x -> x.getStatusIcon() == 'X').toString());
        assertEquals("1. [D][X] deadline (by: Dec 12, 2022)\n2. [E][ ] event (at: Dec 12, 2012)\n", taskList.filter(x -> x.getTaskTypeIcon() != 'T').toString());
        assertEquals("1. [T][X] todo\n2. [D][X] deadline (by: Dec 12, 2022)\n", taskList.filter(x -> x.getDescription().contains("d")).toString());

        try {
            taskList.deleteItem(1);
        } catch (Exception exception) {
            fail();
        }

        assertEquals(2, taskList.size());
        assertEquals("1. [T][X] todo\n2. [E][ ] event (at: Dec 12, 2012)\n", taskList.toString());

        try {
            taskList.deleteItem(2);
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! No such task exists :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }

        try {
            taskList.markItem(-1);
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! No such task exists :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }

        try {
            taskList.unMarkItem(1000000000);
            fail();
        } catch (DukeException exception) {
            assertEquals("OOPS!!! No such task exists :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }

        assertEquals(2, taskList.size());
        assertEquals("1. [T][X] todo\n2. [E][ ] event (at: Dec 12, 2012)\n", taskList.toString());
    }
}
