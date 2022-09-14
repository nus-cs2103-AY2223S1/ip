package ren;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseCommand_bye_success() {
        try {
            Parser parser = new Parser(new TaskListStub());
            assertEquals(" Farewell!\n", parser.parseCommand("bye"));
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_todo_success() {
        try {
            Parser parser = new Parser(new TaskListStub());
            assertEquals("", parser.parseCommand("todo test"));
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_todo_exceptionThrown() {
        try {
            Parser parser = new Parser(new TaskListStub());
            parser.parseCommand("todo");
            fail();
        } catch (RenException e) {
            assertEquals("Please provide a description for the todo.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_deadline_success() {
        try {
            Parser parser = new Parser(new TaskListStub());
            assertEquals("", parser.parseCommand("deadline test /by test"));
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_deadline_exceptionThrown() {
        try {
            Parser parser = new Parser(new TaskListStub());
            parser.parseCommand("deadline");
            fail();
        } catch (RenException e) {
            assertEquals("Please provide a description for the deadline.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_deadline2_exceptionThrown() {
        try {
            Parser parser = new Parser(new TaskListStub());
            parser.parseCommand("deadline test");
            fail();
        } catch (RenException e) {
            assertEquals("Please provide a date/time for the deadline.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_event_success() {
        try {
            Parser parser = new Parser(new TaskListStub());
            assertEquals("", parser.parseCommand("event test /at test"));
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_event_exceptionThrown() {
        try {
            Parser parser = new Parser(new TaskListStub());
            parser.parseCommand("event");
            fail();
        } catch (RenException e) {
            assertEquals("Please provide a description for the event.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_event2_exceptionThrown() {
        try {
            Parser parser = new Parser(new TaskListStub());
            parser.parseCommand("event test");
            fail();
        } catch (RenException e) {
            assertEquals("Please provide a date/time for the event.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_delete_success() {
        try {
            Parser parser = new Parser(new TaskListStub());
            assertEquals("", parser.parseCommand("delete 2"));
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_delete_exceptionThrown() {
        try {
            Parser parser = new Parser(new TaskListStub());
            parser.parseCommand("delete one");
            fail();
        } catch (RenException e) {
            assertEquals("Please indicate the task no. in digits.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_mark_success() {
        try {
            Parser parser = new Parser(new TaskListStub());
            assertEquals("", parser.parseCommand("mark 2"));
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_mark_exceptionThrown() {
        try {
            Parser parser = new Parser(new TaskListStub());
            parser.parseCommand("unmark one");
            fail();
        } catch (RenException e) {
            assertEquals("Please indicate the task no. in digits.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_unmark_success() {
        try {
            Parser parser = new Parser(new TaskListStub());
            assertEquals("", parser.parseCommand("unmark 2"));
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_unmark_exceptionThrown() {
        try {
            Parser parser = new Parser(new TaskListStub());
            parser.parseCommand("unmark one");
            fail();
        } catch (RenException e) {
            assertEquals("Please indicate the task no. in digits.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_list_success() {
        try {
            Parser parser = new Parser(new TaskListStub());
            assertEquals("", parser.parseCommand("list"));
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_find_success() {
        try {
            Parser parser = new Parser(new TaskListStub());
            assertEquals("", parser.parseCommand("find test"));
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_find_exceptionThrown() {
        try {
            Parser parser = new Parser(new TaskListStub());
            parser.parseCommand("find");
            fail();
        } catch (RenException e) {
            assertEquals("Please provide a search term.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_empty_success() {
        try {
            Parser parser = new Parser(new TaskListStub());
            assertEquals("", parser.parseCommand("empty"));
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_blah_exceptionThrown() {
        try {
            Parser parser = new Parser(new TaskListStub());
            parser.parseCommand("blah");
            fail();
        } catch (RenException e) {
            assertEquals("Please enter a supported command.", e.getMessage());
        }
    }
}

