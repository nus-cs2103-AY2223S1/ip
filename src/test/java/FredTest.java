import commands.Command;

import exception.FredException;

import parser.Parser;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import org.junit.jupiter.api.Test;
import tasklist.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FredTest {

    @Test
    public void todoTest() {
        Task task = new ToDo("borrow book");
        assertEquals("[T][ ] borrow book", task.toString());
    }

    @Test
    public void eventTest() {
        LocalDate date = LocalDate.parse("2022-12-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Task task = new Event("party", date);
        assertEquals("[E][ ] party (at: Dec 01 2022)", task.toString());
    }

    @Test
    public void deadlineTest() {
        LocalDate date = LocalDate.parse("2022-09-15", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Task task = new Deadline("assignment", date);
        assertEquals("[D][ ] assignment (by: Sep 15 2022)", task.toString());
    }

    @Test
    public void parserTest() {
        Command command = null;

        try {
            command = Parser.parse("bye");
        } catch (FredException e) {
            System.out.println(e);
        }
        assertEquals(true, command.isExit());
    }

    @Test
    public void tasklistTest() throws FredException {
        TaskList t = new TaskList();
        t.add(new ToDo("borrow book"));
        t.mark(1);
        String result = "";

        try {
            result = t.list();
        } catch (FredException e) {
            System.out.println(e);
        }
        assertEquals("Here are the tasks in your list:\n1.[T][X] borrow book\n", result);
    }
}
