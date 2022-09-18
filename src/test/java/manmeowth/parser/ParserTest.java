package manmeowth.parser;

import manmeowth.command.Command;
import manmeowth.command.HelpCommand;
import manmeowth.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_todo_exceptionThrown() {
        try {
            assertEquals(0, new Parser().parse("todo"));
            fail();
        } catch (Exception e) {
            String msg = "Input 'todo ABC' to add task ABC\n";
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    public void parse_event_exceptionThrown() {
        try {
            assertEquals(0, new Parser().parse("event eat /by tonight"));
            fail();
        } catch (Exception e) {
            String msg = "Input 'event ABC /at DATE' to add event ABC on DATE\n";
            assertEquals(msg, e.getMessage());
        }
    }


    @Test
    public void parse_deadline_exceptionThrown() {
        try {
            assertEquals(0, new Parser().parse("deadline eat /at tonight"));
            fail();
        } catch (Exception e) {
            String msg = "Input 'deadline ABC /by DATE' to add deadline ABC due by DATE\n";
            assertEquals(msg, e.getMessage());
        }
    }


    @Test
    public void parse_mark_exceptionThrown() {
        try {
            assertEquals(0, new Parser().parse("mark"));
            fail();
        } catch (Exception e) {
            String msg = "Input 'mark x' to mark task x as complete\n";
            assertEquals(msg, e.getMessage());
        }
    }


    @Test
    public void parse_unmark_exceptionThrown() {
        Command c = new Parser().parse("unmark eat");
        assertEquals(true, c instanceof HelpCommand);
    }




    @Test
    public void parse_delete_exceptionThrown() {
        try {
            assertEquals(0, new Parser().parse("delete"));
            fail();
        } catch (Exception e) {
            String msg = "Input 'delete x' to delete task x from the list\n";
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    public void parse_invalid_exceptionThrown() {
        Command c = new Parser().parse("blabla eat /by tonight");
        assertEquals(true, c instanceof HelpCommand);
    }

}

