package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DukeTest {
    @Test
    public void addTest() throws DukeException {
        Duke duke = new Duke();
        assertEquals("Got it. I've added this task:\n[D][ ] assignment (by: Sep 19 2022)\n"
                + "Now you have 1 tasks in the list.\n", duke.getResponse("deadline assignment /by 2022-09-19"));
        duke.clear();
    }

    @Test
    public void exceptionTest1() {
        assertEquals("Task description is empty!\n", new Duke().getResponse("todo "));
    }

    @Test
    public void listTest() throws DukeException {
        Duke duke = new Duke();
        duke.getResponse("todo read book");
        duke.getResponse("todo watch movies");
        assertEquals("Here are the tasks in your list:\n1. [T][ ] read book\n2. [T][ ] watch movies\n",
                duke.getResponse("list"));
        duke.clear();
    }

    @Test
    public void markTest() throws DukeException {
        Duke duke = new Duke();
        duke.getResponse("todo read book");
        duke.getResponse("todo watch movies");
        assertEquals("Nice! I've marked this task as done:\n[T][X] read book\n", duke.getResponse("mark 1"));
        duke.clear();
    }

    @Test
    public void exceptionTest2() throws DukeException {
        Duke duke = new Duke();
        duke.getResponse("todo read book");
        duke.getResponse("todo watch movies");
        assertEquals("Index out of bound!\n", duke.getResponse("mark 3"));
        duke.clear();
    }

    @Test
    public void deleteTest() throws DukeException {
        Duke duke = new Duke();
        duke.getResponse("todo read book");
        duke.getResponse("todo watch movies");
        assertEquals("Noted. I've removed this task:\n[T][ ] watch movies\nNow you have 1 tasks in the list.\n",
                duke.getResponse("delete 2"));
        duke.clear();
    }

    @Test
    public void exceptionTest3() throws DukeException {
        Duke duke = new Duke();
        duke.getResponse("todo read book");
        duke.getResponse("todo watch movies");
        assertEquals("Index out of bound!\n", duke.getResponse("delete 0"));
        duke.clear();
    }

    @Test
    public void findTest() throws DukeException {
        Duke duke = new Duke();
        duke.getResponse("todo read book");
        duke.getResponse("todo read magazine");
        duke.getResponse("todo watch movies");
        assertEquals("Here are the matching tasks in your list:\n1. [T][ ] read book\n2. [T][ ] read magazine\n",
                duke.getResponse("find read"));
        duke.clear();
    }

    @Test
    public void freeTest() throws DukeException {
        Duke duke = new Duke();
        duke.getResponse("todo read book");
        duke.getResponse("todo read magazine");
        duke.getResponse("todo watch movies");
        assertEquals("The nearest date without task is "
                + LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ".\n",
                duke.getResponse("free"));
        duke.clear();
    }

    @Test
    public void exitTest() {
        assertEquals("Bye. Hope to see you again soon!\n", new Duke().getResponse("bye"));
    }

    @Test
    public void clearTest() throws DukeException {
        Duke duke = new Duke();
        duke.getResponse("todo read book");
        duke.getResponse("todo read magazine");
        duke.getResponse("todo watch movies");
        duke.clear();
        assertEquals(0, duke.tasks.size());
    }
}
