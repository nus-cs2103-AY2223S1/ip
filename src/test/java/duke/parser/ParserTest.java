package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class ParserTest {
    private final Parser sut = new Parser();

    @Test
    public void parse_list_success() {
        try {
            sut.parse("list");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parse_listWithArgs_exceptionThrown() {
        try {
            sut.parse("list testing");
            fail();
        } catch (DukeException e) {
            assertEquals("list must not have any argument.", e.getMessage());
        }
    }

    @Test
    public void parse_markWithIntArg_success() {
        try {
            sut.parse("mark 1");
            sut.parse("mark 2");
            sut.parse("mark 3");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parse_markWithStrArg_exceptionThrown() {
        try {
            sut.parse("mark todo");
            fail();
        } catch (DukeException e) {
            assertEquals("You must enter the index of the task.", e.getMessage());
        }
    }

    @Test
    public void parse_markWithFloatArg_exceptionThrown() {
        try {
            sut.parse("mark 2.4");
            fail();
        } catch (DukeException e) {
            assertEquals("You must enter the index of the task.", e.getMessage());
        }
    }

    @Test
    public void parse_markWithoutArg_exceptionThrown() {
        try {
            sut.parse("mark");
            fail();
        } catch (DukeException e) {
            assertEquals("You must enter the index of the task.", e.getMessage());
        }
    }

    @Test
    public void parse_unmarkWithIntArg_success() {
        try {
            sut.parse("unmark 1");
            sut.parse("unmark 2");
            sut.parse("unmark 3");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parse_unmarkWithStrArg_exceptionThrown() {
        try {
            sut.parse("unmark todo");
            fail();
        } catch (DukeException e) {
            assertEquals("You must enter the index of the task.", e.getMessage());
        }
    }

    @Test
    public void parse_unmarkWithFloatArg_exceptionThrown() {
        try {
            sut.parse("unmark 1.1");
            fail();
        } catch (DukeException e) {
            assertEquals("You must enter the index of the task.", e.getMessage());
        }
    }

    @Test
    public void parse_unmarkWithoutArg_exceptionThrown() {
        try {
            sut.parse("unmark");
            fail();
        } catch (DukeException e) {
            assertEquals("You must enter the index of the task.", e.getMessage());
        }
    }

    @Test
    public void parse_deleteWithIntArg_success() {
        try {
            sut.parse("delete 1");
            sut.parse("delete 2");
            sut.parse("delete 3");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parse_deleteWithStrArg_exceptionThrown() {
        try {
            sut.parse("delete todo");
            fail();
        } catch (DukeException e) {
            assertEquals("You must enter the index of the task.", e.getMessage());
        }
    }

    @Test
    public void parse_deleteWithFloatArg_exceptionThrown() {
        try {
            sut.parse("delete 1.2");
            fail();
        } catch (DukeException e) {
            assertEquals("You must enter the index of the task.", e.getMessage());
        }
    }

    @Test
    public void parse_deleteWithoutArg_exceptionThrown() {
        try {
            sut.parse("delete");
            fail();
        } catch (DukeException e) {
            assertEquals("You must enter the index of the task.", e.getMessage());
        }
    }

    @Test
    public void parse_todoWithValidArg_success() {
        try {
            sut.parse("todo testing todo");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parse_todoWithoutArg_exceptionThrown() {
        try {
            sut.parse("todo");
            fail();
        } catch (DukeException e) {
            assertEquals("A todo must contain a description.", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineWithValidArg_success() {
        try {
            sut.parse("deadline testing deadline /by 2020-11-12");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parse_deadlineWithoutDesc_exceptionThrown() {
        try {
            sut.parse("deadline /by 2020-11-12");
            fail();
        } catch (DukeException e) {
            assertEquals("A deadline must contain a description and a due date.", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineWithoutDate_exceptionThrown() {
        try {
            sut.parse("deadline testing deadline /by");
            fail();
        } catch (DukeException e) {
            assertEquals("A deadline must contain a description and a due date.", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineWithoutByFlag_exceptionThrown() {
        try {
            sut.parse("deadline testing deadline 2020-11-12");
            fail();
        } catch (DukeException e) {
            assertEquals("A deadline must contain a description and a due date.", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineWithoutArg_exceptionThrown() {
        try {
            sut.parse("deadline");
            fail();
        } catch (DukeException e) {
            assertEquals("A deadline must contain a description and a due date.", e.getMessage());
        }
    }

    @Test
    public void parse_eventWithValidArg_success() {
        try {
            sut.parse("event testing event /at 2020-11-12");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parse_eventWithoutDesc_exceptionThrown() {
        try {
            sut.parse("event /at 2020-11-12");
            fail();
        } catch (DukeException e) {
            assertEquals("An event must contain a description and a date.", e.getMessage());
        }
    }

    @Test
    public void parse_eventWithoutDate_exceptionThrown() {
        try {
            sut.parse("event testing event /at");
            fail();
        } catch (DukeException e) {
            assertEquals("An event must contain a description and a date.", e.getMessage());
        }
    }

    @Test
    public void parse_eventWithoutAtFlag_exceptionThrown() {
        try {
            sut.parse("event testing event 2020-11-12");
            fail();
        } catch (DukeException e) {
            assertEquals("An event must contain a description and a date.", e.getMessage());
        }
    }

    @Test
    public void parse_eventWithoutArg_exceptionThrown() {
        try {
            sut.parse("event");
            fail();
        } catch (DukeException e) {
            assertEquals("An event must contain a description and a date.", e.getMessage());
        }
    }

    @Test
    public void parse_findWithoutArg_success() {
        try {
            sut.parse("find");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parse_findWithArg_success() {
        try {
            sut.parse("find test");
            sut.parse("find multiple words");
            sut.parse("find containing integers 132");
            sut.parse("find containing symbols %");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parse_sortAscending_success() {
        try {
            sut.parse("sort ascending");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parse_sortDescending_success() {
        try {
            sut.parse("sort descending");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parse_sortWithoutArg_exceptionThrown() {
        try {
            sut.parse("sort");
            fail();
        } catch (DukeException e) {
            assertEquals("Sort must be correctly specified: descending or ascending.", e.getMessage());
        }
    }

    @Test
    public void parse_sortWithInvalidArg_exceptionThrown() {
        try {
            sut.parse("sort date");
            fail();
        } catch (DukeException e) {
            assertEquals("Sort must be correctly specified: descending or ascending.", e.getMessage());
        }
    }


    @Test
    public void parse_bye_success() {
        try {
            sut.parse("bye");
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parse_byeWithArgs_exceptionThrown() {
        try {
            sut.parse("bye withArgs");
        } catch (DukeException e) {
            assertEquals("bye must not have any argument.", e.getMessage());
        }
    }

    @Test
    public void parse_unknownCommand_exceptionThrown() {
        try {
            sut.parse("quit");
        } catch (DukeException e) {
            assertEquals("Sorry, I don't understand what that means :(", e.getMessage());
        }
    }

}
