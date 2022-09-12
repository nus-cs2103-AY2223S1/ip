package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ParserTest {
    private final Storage storage = new Storage(Duke.FILE_PATH);
    private final TaskList taskList = new TaskList(storage);
    private final Parser parser = new Parser(taskList);

    /**
     * Deletes the file that was created.
     */
    @AfterEach
    void tearDown() {
        final File file = new File(Duke.FILE_PATH);
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
                + "Now you have 1 task in the list.\n", parser.parseUserCommand(
                "todo borrow book"));
        assertEquals("Here are the tasks in your list:\n"
                        + "  1. [T][ ] borrow book\n",
                parser.parseUserCommand("list"));
        assertEquals("Nice! I've marked this task as done:\n"
                        + "    [T][X] borrow book\n",
                parser.parseUserCommand("mark 1"));
        assertEquals("Got it. I've added this task:\n"
                        + "    [D][ ] return book (by: 24 Aug 2022 14:00)\n"
                        + "Now you have 2 tasks in the list.\n",
                parser.parseUserCommand("deadline return book /by 2022-08-24 14:00"));
        assertEquals("Got it. I've added this task:\n"
                        + "    [E][ ] project meeting (at: 31 Dec 2023 23:59)\n"
                        + "Now you have 3 tasks in the list.\n",
                parser.parseUserCommand("event project meeting /at 2023-12-31 23:59"));

        CustomMessageException todoThrows = assertThrows(CustomMessageException.class, () -> parser.parseUserCommand(
                "todo"), "Expected parses to throw, but it didn't");

        assertTrue(todoThrows.getMessage().contains("OOPS!!! The description of a todo cannot be empty."));

        CustomMessageException blahThrows = assertThrows(CustomMessageException.class, () -> parser.parseUserCommand(
                "blah"), "Expected parses to throw, but it didn't");

        assertTrue(blahThrows.getMessage().contains("OOPS!!! I'm sorry, but I don't know what that "
                + "means :-("));

        assertEquals("Noted. I've removed this task:\n"
                        + "    [D][ ] return book (by: 24 Aug 2022 14:00)\n"
                        + "Now you have 2 tasks in the list.\n",
                parser.parseUserCommand("delete 2"));
        assertEquals("Here are the tasks in your list:\n"
                        + "  1. [T][X] borrow book\n"
                        + "  2. [E][ ] project meeting (at: 31 Dec 2023 23:59)\n",
                parser.parseUserCommand("list"));
        assertEquals("Got it. I've added this task:\n"
                        + "    [D][ ] return book (by: 24 Aug 2022 14:00)\n"
                        + "Now you have 3 tasks in the list.\n",
                parser.parseUserCommand("deadline return book /by 2022-08-24 14:00"));
    }
}
