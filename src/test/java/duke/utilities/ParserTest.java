package duke.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseCommand_hello_correct() {
        try {
            assertEquals("hello|", Parser.parseCommand("hello"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseCommand_bye_correct() {
        try {
            assertEquals("bye|", Parser.parseCommand("bye"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseCommand_list_correct() {
        try {
            assertEquals("list|", Parser.parseCommand("list"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseCommand_markNoTask_correct() {
        try {
            assertEquals("mark|-1", Parser.parseCommand("mark"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseCommand_markTask_correct() {
        try {
            assertEquals("mark|1", Parser.parseCommand("mark 1"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseCommand_unmarkNoTask_correct() {
        try {
            assertEquals("unmark|-1", Parser.parseCommand("unmark"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseCommand_unmarkTask_correct() {
        try {
            assertEquals("unmark|1", Parser.parseCommand("unmark 1"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseCommand_deleteNoTask_correct() {
        try {
            assertEquals("delete|-1", Parser.parseCommand("delete"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseCommand_deleteTask_correct() {
        try {
            assertEquals("delete|1", Parser.parseCommand("delete 1"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseCommand_findNoTarget_correct() {
        try {
            assertEquals("find|", Parser.parseCommand("find"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseCommand_findTarget_correct() {
        try {
            assertEquals("find|book", Parser.parseCommand("find book"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseCommand_remindNoType_exceptionThrown() {
        DukeException de = assertThrows(DukeException.class, () -> {
            Parser.parseCommand("remind");
        });

        assertEquals("You can only get reminders for deadline or event tasks!", de.getMessage());
    }

    @Test
    public void parseCommand_remindType_correct() {
        try {
            assertEquals("remind|deadline", Parser.parseCommand("remind deadline"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseCommand_todoEmptyDescription_exceptionThrown() {
        DukeException de = assertThrows(DukeException.class, () -> {
            Parser.parseCommand("todo");
        });

        assertEquals("The description of a todo should not be empty!", de.getMessage());
    }

    @Test
    public void parseCommand_todoDescription_correct() {
        try {
            assertEquals("todo|read book", Parser.parseCommand("todo read book"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseCommand_deadlineEmptyDescriptionOrBy_exceptionThrown() {
        DukeException de = assertThrows(DukeException.class, () -> {
            Parser.parseCommand("deadline");
        });

        assertEquals("The description/by time of a deadline should not be empty!", de.getMessage());
    }

    @Test
    public void parseCommand_deadlineDescription_correct() {
        try {
            assertEquals(
                    "deadline|return book|2022-09-21 18:00",
                    Parser.parseCommand("deadline return book /by 2022-09-21 18:00")
            );
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventEmptyDescriptionOrFromOrEnd_exceptionThrown() {
        DukeException de = assertThrows(DukeException.class, () -> {
            Parser.parseCommand("event");
        });

        assertEquals("The description/from/end of an event should not be empty!", de.getMessage());
    }

    @Test
    public void parseCommand_eventDescription_correct() {
        try {
            assertEquals(
                    "event|library event|2022-09-21 18:00|2022-09-21 19:00",
                    Parser.parseCommand("event library event /from 2022-09-21 18:00 /to 2022-09-21 19:00")
            );
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseCommand_default_correct() {
        try {
            assertEquals("", Parser.parseCommand(""));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
