package duke;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ParserTest {

    @Test
    public void parse_list_command_success() {
        try {
            Parser.parse("list");
        } catch (DukeException e) {
            assert(false);
        }
    }

    @Test
    public void parse_list_command_throwsException() {
        try {
            Parser.parse("list");
        } catch (DukeException e) {
            assertEquals("Single commands should not have any text behind it\n", e.getMessage());
        }
    }

    @Test
    public void parse_bye_command_throwsException() {
        try {
            Parser.parse("bye");
        } catch (DukeException e) {
            assertEquals("I've saved your tasks! Come back soon ~\n", e.getMessage());
        }
    }

    @Test
    public void parse_help_command_throwsException() {
        try {
            Parser.parse("help");
        } catch (DukeException e) {
            assertEquals("Need help? You can click on the \"?\" icon beside the send button to"
                    + " see the list of available commands!", e.getMessage());
        }
    }

    @Test
    public void parse_todo_command_noDescription_throwsException() {
        try {
            Parser.parse("todo");
        } catch (DukeException e) {
            assertEquals(Constants.MISSING_DESCRIPTION_MESSAGE, e.getMessage());
        }
    }

}
