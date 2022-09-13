package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import duke.tasklist.TaskList;

class ParserTest {
    private final Storage storage = new Storage();
    private final TaskList taskList = new TaskList();
    private final Parser parser = new Parser();

    /**
     * Deletes the file that was created.
     */
    @AfterEach
    void tearDown() {
        final File file = new File(Storage.FILE_PATH);
        if (file.exists() && !file.delete()) {
            throw new RuntimeException("Could not delete file");
        }
    }
    /**
     * Tests all possible commands, except find. Improper input and improper commands that throw exceptions are also
     * tested for.
     *
     * @throws CustomMessageException for improper input
     */
    @Test
    public void parseUserCommand_actualCommand_performsAction() throws CustomMessageException {
        assertEquals("Got it. I've added this task:\n"
                + "    [T][ ] borrow book\n"
                + "Now you have 1 task in the list.", parser.parseUserCommand(
                "todo borrow book").execute(storage, taskList));
        assertEquals("Here are the tasks in your list:\n"
                        + "  1. [T][ ] borrow book",
                parser.parseUserCommand("list").execute(storage, taskList));
        assertEquals("Nice! I've marked this task as done:\n"
                        + "    [T][X] borrow book",
                parser.parseUserCommand("mark 1").execute(storage, taskList));
        assertEquals("Got it. I've added this task:\n"
                        + "    [D][ ] return book (by: 24 Aug 2022 14:00)\n"
                        + "Now you have 2 tasks in the list.",
                parser.parseUserCommand("deadline return book /by 2022-08-24 14:00").execute(storage, taskList));
        assertEquals("Got it. I've added this task:\n"
                        + "    [E][ ] project meeting (at: 31 Dec 2023 23:59)\n"
                        + "Now you have 3 tasks in the list.",
                parser.parseUserCommand("event project meeting /at 2023-12-31 23:59").execute(storage, taskList));

        CustomMessageException todoThrows = assertThrows(CustomMessageException.class, () -> parser.parseUserCommand(
                "todo").execute(storage, taskList), "Expected parses to throw, but it didn't");

        assertTrue(todoThrows.getMessage().contains("OOPS!!! The todo must have valid arguments."));

        CustomMessageException blahThrows = assertThrows(CustomMessageException.class, () -> parser.parseUserCommand(
                "blah").execute(storage, taskList), "Expected parses to throw, but it didn't");

        assertTrue(blahThrows.getMessage().contains("OOPS!!! I'm sorry, but I don't know what that "
                + "means :-("));

        assertEquals("Noted. I've removed this task:\n"
                        + "    [D][ ] return book (by: 24 Aug 2022 14:00)\n"
                        + "Now you have 2 tasks in the list.",
                parser.parseUserCommand("delete 2").execute(storage, taskList));
        assertEquals("Here are the tasks in your list:\n"
                        + "  1. [T][X] borrow book\n"
                        + "  2. [E][ ] project meeting (at: 31 Dec 2023 23:59)",
                parser.parseUserCommand("list").execute(storage, taskList));
        assertEquals("Got it. I've added this task:\n"
                        + "    [D][ ] return book (by: 24 Aug 2022 14:00)\n"
                        + "Now you have 3 tasks in the list.",
                parser.parseUserCommand("deadline return book /by 2022-08-24 14:00").execute(storage, taskList));
    }
}
