package duke.task;
import duke.common.DukeException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {
    @Test
    void testDecodeToDo() {
        try {
            Task task = Task.decode("T |   | my todo");
            assertEquals('T', task.getTaskTypeIcon());
            assertEquals(' ', task.getStatusIcon());
            assertEquals("T |   | my todo", task.encode());
            assertEquals("[T][ ] my todo", task.toString());
        } catch (Exception exception) {
            fail();
        }
        try {
            Task task = Task.decode("T | X | my todo");
            assertEquals('T', task.getTaskTypeIcon());
            assertEquals('X', task.getStatusIcon());
            assertEquals("T | X | my todo", task.encode());
            assertEquals("[T][X] my todo", task.toString());
        } catch (Exception exception) {
            fail();
        }
        try {
            Task task = Task.decode("T | X | my todo | extra");
            assertEquals('T', task.getTaskTypeIcon());
            assertEquals('X', task.getStatusIcon());
            assertEquals("T | X | my todo", task.encode());
            assertEquals("[T][X] my todo", task.toString());
        } catch (Exception exception) {
            fail();
        }
        try {
            Task task = Task.decode("T |");
            fail();
        } catch (DukeException exception) {
            assertEquals("☹ OOPS!!! Invalid encoded format :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Task task = Task.decode("ToDo | X | is todo");
            fail();
        } catch (DukeException exception) {
            assertEquals("☹ OOPS!!! Invalid encoded format :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Task task = Task.decode("ToDo | X |");
            fail();
        } catch (DukeException exception) {
            assertEquals("☹ OOPS!!! Invalid encoded format :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    void testDecodeEvent() {
        try {
            Task task = Task.decode("E |   | my todo | 2012-02-29");
            assertEquals('E', task.getTaskTypeIcon());
            assertEquals(' ', task.getStatusIcon());
            assertEquals("E |   | my todo | 2012-02-29", task.encode());
            assertEquals("[E][ ] my todo (at: Feb 29, 2012)", task.toString());
        } catch (Exception exception) {
            fail();
        }
        try {
            Task task = Task.decode("E | X | my todo | 2012-02-29");
            assertEquals('E', task.getTaskTypeIcon());
            assertEquals('X', task.getStatusIcon());
            assertEquals("E | X | my todo | 2012-02-29", task.encode());
            assertEquals("[E][X] my todo (at: Feb 29, 2012)", task.toString());
        } catch (Exception exception) {
            fail();
        }
        try {
            Task task = Task.decode("E | X | my todo | 2012-02-29 | extra");
            assertEquals('E', task.getTaskTypeIcon());
            assertEquals('X', task.getStatusIcon());
            assertEquals("E | X | my todo | 2012-02-29", task.encode());
            assertEquals("[E][X] my todo (at: Feb 29, 2012)", task.toString());
        } catch (Exception exception) {
            fail();
        }
        try {
            Task task = Task.decode("E | X | my todo | 2012-02-30");
            fail();
        } catch (DukeException exception) {
            assertEquals("☹ OOPS!!! Can't recognize the date :(. Please input the date in yyyy-mm-dd format.", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Task task = Task.decode("E | X | my todo");
            fail();
        } catch (DukeException exception) {
            assertEquals("☹ OOPS!!! Invalid encoded format :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Task task = Task.decode("Event | X | my todo");
            fail();
        } catch (DukeException exception) {
            assertEquals("☹ OOPS!!! Invalid encoded format :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
    }

    @Test
    void testDecodeDeadline() {
        try {
            Task task = Task.decode("D |   | my todo | 2012-02-29");
            assertEquals('D', task.getTaskTypeIcon());
            assertEquals(' ', task.getStatusIcon());
            assertEquals("D |   | my todo | 2012-02-29", task.encode());
            assertEquals("[D][ ] my todo (by: Feb 29, 2012)", task.toString());
        } catch (Exception exception) {
            fail();
        }
        try {
            Task task = Task.decode("D | X | my todo | 2012-02-29");
            assertEquals('D', task.getTaskTypeIcon());
            assertEquals('X', task.getStatusIcon());
            assertEquals("D | X | my todo | 2012-02-29", task.encode());
            assertEquals("[D][X] my todo (by: Feb 29, 2012)", task.toString());
        } catch (Exception exception) {
            fail();
        }
        try {
            Task task = Task.decode("D | X | my todo | 2012-02-29 | extra");
            assertEquals('D', task.getTaskTypeIcon());
            assertEquals('X', task.getStatusIcon());
            assertEquals("D | X | my todo | 2012-02-29", task.encode());
            assertEquals("[D][X] my todo (by: Feb 29, 2012)", task.toString());
        } catch (Exception exception) {
            fail();
        }
        try {
            Task task = Task.decode("D | X | my todo | 2012-02-30");
            fail();
        } catch (DukeException exception) {
            assertEquals("☹ OOPS!!! Can't recognize the date :(. Please input the date in yyyy-mm-dd format.", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Task task = Task.decode("D | X | my todo");
            fail();
        } catch (DukeException exception) {
            assertEquals("☹ OOPS!!! Invalid encoded format :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
        try {
            Task task = Task.decode("Deadline | X | my todo");
            fail();
        } catch (DukeException exception) {
            assertEquals("☹ OOPS!!! Invalid encoded format :(", exception.getMessage());
        } catch (Exception exception) {
            fail();
        }
    }
}
