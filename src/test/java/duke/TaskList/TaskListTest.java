package duke.tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

public class TaskListTest {
    @Test
    public void addToDoTest() {
        TaskList list = new TaskList();
        assertEquals(list.toString(), "No such task, my friend.");
        list.addTask(new ToDo("Return book"));
        assertEquals(list.toString(), "1.[T][✘] Return book\n");
    }

    @Test
    public void addDeadlineTest() {
        TaskList list = new TaskList();
        assertEquals(list.toString(), "No such task, my friend.");
        list.addTask(new Deadline("Return book", "12/12/2000"));
        assertEquals(list.toString(), "1.[D][✘] Return book (by: 12/12/2000 0000)\n");
    }

    @Test
    public void addEventTest() {
        TaskList list = new TaskList();
        assertEquals(list.toString(), "No such task, my friend.");
        list.addTask(new Event("Return book", "12/12/2000"));
        assertEquals(list.toString(), "1.[E][✘] Return book (at: 12/12/2000 0000)\n");
    }

    @Test
    public void deleteTest() throws DukeException {
        TaskList list = new TaskList();
        assertEquals(list.toString(), "No such task, my friend.");
        list.addTask(new ToDo("Return book"));
        assertEquals(list.toString(), "1.[T][✘] Return book\n");
        list.delete("1");
        assertEquals(list.toString(), "No such task, my friend.");
    }

    @Test
    public void findTest() throws DukeException {
        TaskList list = new TaskList();
        assertEquals(list.toString(), "No such task, my friend.");
        list.addTask(new ToDo("Return book"));
        assertEquals(list.toString(), "1.[T][✘] Return book\n");
        assertEquals(list.find("joke"), "No such task, my friend.");
        assertEquals(list.find("book"), "1.[T][✘] Return book\n");
    }

    @Test
    public void beforeTest() throws DukeException {
        TaskList list = new TaskList();
        assertEquals(list.toString(), "No such task, my friend.");
        list.addTask(new Event("Return book", "12/12/2000"));
        assertEquals(list.toString(), "1.[E][✘] Return book (at: 12/12/2000 0000)\n");
        assertEquals(list.printDeadline("12/12/2000"), "No such task, my friend.");
        assertEquals(list.printDeadline("12/12/2020"), "1.[E][✘] Return book (at: 12/12/2000 0000)\n");
    }

    @Test
    public void doneTest() throws DukeException {
        TaskList list = new TaskList();
        assertEquals(list.toString(), "No such task, my friend.");
        list.addTask(new ToDo("Return book"));
        assertEquals(list.toString(), "1.[T][✘] Return book\n");
        list.markDone("1");
        assertEquals(list.toString(), "1.[T][✓] Return book\n");
        list.markUndone("1");
        assertEquals(list.toString(), "1.[T][✘] Return book\n");
    }
}
