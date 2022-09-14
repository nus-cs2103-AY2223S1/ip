package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.tasks.TaskList;

public class CommandParserTest {
    @Test
    public void addToDoTest() {
        TaskList list = new TaskList();
        CommandParser parser = new CommandParser(list);
        assertEquals(parser.handle("todo Return book"),
                "Wow, so productive... "
                        + "\n[T][✘] Return book "
                        + "\n1 outstanding tasks");
    }

    @Test
    public void addDeadlineTest() {
        TaskList list = new TaskList();
        CommandParser parser = new CommandParser(list);
        assertEquals(parser.handle("deadline Return book 12/12/2000"),
                "Wow, so productive... "
                        + "\n[D][✘] Return book (by: 12/12/2000 0000) "
                        + "\n1 outstanding tasks");
    }

    @Test
    public void addEventTest() {
        TaskList list = new TaskList();
        CommandParser parser = new CommandParser(list);
        assertEquals(parser.handle("event Return book 12/12/2000"),
                "Wow, so productive... "
                        + "\n[E][✘] Return book (at: 12/12/2000 0000) "
                        + "\n1 outstanding tasks");
    }

    @Test
    public void deleteTest() {
        TaskList list = new TaskList();
        CommandParser parser = new CommandParser(list);
        parser.handle("todo Return book");
        assertEquals(parser.handle("delete 1"),
                "Giving up, huh? "
                        + "\n[T][✘] Return book "
                        + "\n0 outstanding tasks");
    }

    @Test
    public void findTest() {
        TaskList list = new TaskList();
        CommandParser parser = new CommandParser(list);
        parser.handle("todo Return book");
        assertEquals(parser.handle("find book"),
                        "1.[T][✘] Return book\n");
    }

    @Test
    public void beforeTest() {
        TaskList list = new TaskList();
        CommandParser parser = new CommandParser(list);
        parser.handle("event Return book 12/12/2000");
        assertEquals(parser.handle("before 12/12/2025"),
                "1.[E][✘] Return book (at: 12/12/2000 0000)\n");
        assertEquals(parser.handle("before 12/12/1999"),
                "No such task, my friend.");
    }

    @Test
    public void doneTest() {
        TaskList list = new TaskList();
        CommandParser parser = new CommandParser(list);
        parser.handle("todo Return book");
        assertEquals(parser.handle("done 1"),
                "Finally getting something done, huh? "
                        + "\n[T][✓] Return book");
        assertEquals(parser.handle("undone 1"),
                "One step forward. Two step backwards. "
                        + "\n[T][✘] Return book");
    }

    @Test
    public void listTest() {
        TaskList list = new TaskList();
        CommandParser parser = new CommandParser(list);
        parser.handle("todo Return book");
        assertEquals(parser.handle("list"),
                "Think you are free?\n"
                        + "1.[T][✘] Return book\n");
    }
}
