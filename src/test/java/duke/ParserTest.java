package duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    Storage storage = new Storage("tasks.txt");
    private final TaskList taskList = new TaskList(storage);
    private final Parser parser = new Parser(taskList);

    @AfterEach
    void tearDown() {
        final File file = new File("tasks.txt");
        if (file.exists() && !file.delete()) {
            throw new RuntimeException("Could not delete file");
        }
    }

//    featureUnderTest_testScenario_expectedBehavior

    @Test
    public void parseUserCommand_actualCommand_performsAction() throws CustomMessageException {
        assertEquals("     ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [T][ ] borrow book\n" +
                "     Now you have 1 task in the list.\n" +
                "     ____________________________________________________________", parser.parseUserCommand(
                "todo borrow book", parser.breakLoopIndicator));
        assertEquals("     ____________________________________________________________\n" +
                        "     Here are the tasks in your list:\n" +
                        "      1.[T][ ] borrow book\n" +
                        "     ____________________________________________________________",
                parser.parseUserCommand("list", parser.breakLoopIndicator));
        assertEquals("     ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done:\n" +
                        "       [T][X] borrow book\n" +
                        "     ____________________________________________________________",
                parser.parseUserCommand("mark 1", parser.breakLoopIndicator));
        assertEquals("     ____________________________________________________________\n" +
                        "     Got it. I've added this task:\n" +
                        "       [D][ ] return book (by: 24 Aug 2022 14:00)\n" +
                        "     Now you have 2 tasks in the list.\n" +
                        "     ____________________________________________________________",
                parser.parseUserCommand("deadline return book /by 2022-08-24 14:00",
                        parser.breakLoopIndicator));
        assertEquals("     ____________________________________________________________\n" +
                        "     Got it. I've added this task:\n" +
                        "       [E][ ] project meeting (at: 31 Dec 2023 23:59)\n" +
                        "     Now you have 3 tasks in the list.\n" +
                        "     ____________________________________________________________",
                parser.parseUserCommand("event project meeting /at 2023-12-31 23:59",
                        parser.breakLoopIndicator));

        CustomMessageException todoThrows = assertThrows(CustomMessageException.class, () -> parser.parseUserCommand(
                "todo", parser.breakLoopIndicator), "Expected parses to throw, but it didn't");

        assertTrue(todoThrows.getMessage().contains("☹ OOPS!!! The description of a todo cannot be empty."));

        CustomMessageException blahThrows = assertThrows(CustomMessageException.class, () -> parser.parseUserCommand(
                "blah", parser.breakLoopIndicator), "Expected parses to throw, but it didn't");

        assertTrue(blahThrows.getMessage().contains("☹ OOPS!!! I'm sorry, but I don't know what that " +
                "means :-("));

        assertEquals("     ____________________________________________________________\n" +
                        "     Noted. I've removed this task:\n" +
                        "       [D][ ] return book (by: 24 Aug 2022 14:00)\n" +
                        "     Now you have 2 tasks in the list.\n" +
                        "     ____________________________________________________________",
                parser.parseUserCommand("delete 2", parser.breakLoopIndicator));
        assertEquals("     ____________________________________________________________\n" +
                        "     Here are the tasks in your list:\n" +
                        "      1.[T][X] borrow book\n" +
                        "      2.[E][ ] project meeting (at: 31 Dec 2023 23:59)\n" +
                        "     ____________________________________________________________",
                parser.parseUserCommand("list", parser.breakLoopIndicator));
        assertEquals("     ____________________________________________________________\n" +
                        "     Got it. I've added this task:\n" +
                        "       [D][ ] return book (by: 24 Aug 2022 14:00)\n" +
                        "     Now you have 3 tasks in the list.\n" +
                        "     ____________________________________________________________",
                parser.parseUserCommand("deadline return book /by 2022-08-24 14:00",
                        parser.breakLoopIndicator));
    }
}
