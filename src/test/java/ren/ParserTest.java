package ren;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {
    private final TaskList stub = new TaskListStub();

    @Test
    public void parseCommand_bye_success() {
        try {
            assertEquals(" Farewell!\n", Parser.parseCommand("bye", stub));
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_todo_success() {
        try {
            assertEquals("", Parser.parseCommand("todo test", stub));
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_todo_exceptionThrown() {
        try {
            Parser.parseCommand("todo", stub);
            fail();
        } catch (RenException e) {
            assertEquals("Please provide a description for the todo.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_deadline_success() {
        try {
            assertEquals("", Parser.parseCommand("deadline test /by test", stub));
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_deadline_exceptionThrown() {
        try {
            Parser.parseCommand("deadline", stub);
            fail();
        } catch (RenException e) {
            assertEquals("Please provide a description for the deadline.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_deadline2_exceptionThrown() {
        try {
            Parser.parseCommand("deadline test", stub);
            fail();
        } catch (RenException e) {
            assertEquals("Please provide a date/time for the deadline.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_event_success() {
        try {
            assertEquals("", Parser.parseCommand("event test /at test", stub));
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_event_exceptionThrown() {
        try {
            Parser.parseCommand("event", stub);
            fail();
        } catch (RenException e) {
            assertEquals("Please provide a description for the event.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_event2_exceptionThrown() {
        try {
            Parser.parseCommand("event test", stub);
            fail();
        } catch (RenException e) {
            assertEquals("Please provide a date/time for the event.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_delete_success() {
        try {
            assertEquals("", Parser.parseCommand("delete 2", stub));
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_delete_exceptionThrown() {
        try {
            Parser.parseCommand("delete one", stub);
            fail();
        } catch (RenException e) {
            assertEquals("Please indicate the task no. in digits.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_mark_success() {
        try {
            assertEquals("", Parser.parseCommand("mark 2", stub));
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_mark_exceptionThrown() {
        try {
            Parser.parseCommand("unmark one", stub);
            fail();
        } catch (RenException e) {
            assertEquals("Please indicate the task no. in digits.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_unmark_success() {
        try {
            assertEquals("", Parser.parseCommand("unmark 2", stub));
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_unmark_exceptionThrown() {
        try {
            Parser.parseCommand("unmark one", stub);
            fail();
        } catch (RenException e) {
            assertEquals("Please indicate the task no. in digits.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_list_success() {
        try {
            assertEquals("", Parser.parseCommand("list", stub));
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_find_success() {
        try {
            assertEquals("", Parser.parseCommand("find test", stub));
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_find_exceptionThrown() {
        try {
            Parser.parseCommand("find", stub);
            fail();
        } catch (RenException e) {
            assertEquals("Please provide a search term.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_empty_success() {
        try {
            assertEquals("", Parser.parseCommand("empty", stub));
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_blah_exceptionThrown() {
        try {
            Parser.parseCommand("blah", stub);
            fail();
        } catch (RenException e) {
            assertEquals("Please enter a supported command.", e.getMessage());
        }
    }
}

