package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void exitTest() throws DukeException {
        assertEquals(new ExitCommand(), Parser.parseInput("bye"));
    }

    @Test
    public void listTest() throws DukeException {
        assertEquals(new ListCommand(), Parser.parseInput("list"));
    }

    @Test
    public void freeTest() throws DukeException {
        assertEquals(new FreeCommand(), Parser.parseInput("free"));
    }

    @Test
    public void markTest() throws DukeException {
        assertEquals(new MarkCommand(99, true), Parser.parseInput("mark 100"));
    }

    @Test
    public void unmarkTest() throws DukeException {
        assertEquals(new MarkCommand(49, false), Parser.parseInput("unmark 50"));
    }

    @Test
    public void findTest() throws DukeException {
        assertEquals(new FindCommand("study"), Parser.parseInput("find study"));
    }

    @Test
    public void toDoTest() throws DukeException {
        assertEquals(new AddCommand('T', "study with friends"),
                Parser.parseInput("todo study with friends"));
    }

    @Test
    public void deadlineTest() throws DukeException {
        assertEquals(new AddCommand('D', "submit assignment", "2022-09-19"),
                Parser.parseInput("deadline submit assignment /by 2022-09-19"));
    }

    @Test
    public void eventTest() throws DukeException {
        assertEquals(new AddCommand('E', "go to the beach", "2022-09-30"),
                Parser.parseInput("event go to the beach /at 2022-09-30"));
    }

    @Test
    public void deleteTest() throws DukeException {
        assertEquals(new DeleteCommand(0), Parser.parseInput("delete 1"));
    }

    @Test
    public void exceptionTest1() {
        try {
            Parser.parseInput("hello");
        } catch (DukeException e) {
            assertEquals(new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-("), e);
        }
    }

    @Test
    public void exceptionTest2() {
        try {
            Parser.parseInput("deadline new task");
        } catch (DukeException e) {
            assertEquals(new DukeException("Incorrect syntax!"), e);
        }
    }
}
