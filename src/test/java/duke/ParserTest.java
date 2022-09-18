package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import duke.tasklist.TaskList;

class ParserTest {
    private final Storage storage = new Storage();
    private final TaskList taskList = new TaskList();

    /**
     * Delete any generated files
     */
    @BeforeAll
    static void clean() {
        ParserTest.deleteStorageFiles();
    }

    /**
     * Class that tests commands when there is no previous user data
     */
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class NoPreviousUserData {
        /**
         * Delete all generated files
         */
        @AfterAll
        void tearDown() {
            ParserTest.deleteStorageFiles();
        }
        /**
         * Tests possible commands.
         *
         * @throws CustomMessageException for improper input
         */
        @Test
        public void parseInitialUndo_undoCommand_undoUnsuccessful() throws CustomMessageException {
            assertEquals("Nothing to undo!",
                    Parser.parseUserCommand("undo").execute(storage, taskList));
        }

        /**
         * Checks if an empty TODO description throws an exception
         */
        @Test
        public void parseTodoCommand_emptyDescription_exceptionThrown() {
            CustomMessageException todoThrows = assertThrows(CustomMessageException.class, ()
                            -> Parser.parseUserCommand("todo").execute(storage, taskList),
                    "Expected parser to throw, but it didn't");

            assertTrue(todoThrows.getMessage().contains("OOPS!!! The todo must have valid arguments."));
        }

        /**
         * Checks if empty EVENT arguments throws an exception
         */
        @Test
        public void parseEventCommand_emptyArgs_exceptionThrown() {
            CustomMessageException eventThrowsNoValidArgs = assertThrows(CustomMessageException.class, () ->
                            Parser.parseUserCommand("event").execute(storage, taskList),
                    "Expected parser to throw, but it didn't");

            assertTrue(eventThrowsNoValidArgs.getMessage().contains("OOPS!!! The event must have valid arguments."));
        }

        /**
         * Checks if an empty EVENT description throws an exception
         */
        @Test
        public void parseEventCommand_emptyDescription_exceptionThrown() {
            CustomMessageException eventThrowsNoValidDescription = assertThrows(CustomMessageException.class, () ->
                            Parser.parseUserCommand("event /at 2023-12-31 23:59").execute(storage, taskList),
                    "Expected parser to throw, but it didn't");

            assertTrue(eventThrowsNoValidDescription.getMessage().contains("OOPS!!! "
                    + "The description of a event cannot be empty."));
        }

        /**
         * Checks if an empty EVENT description throws an exception
         */
        @Test
        public void parseUnkownCommand_randomText_exceptionThrown() {
            CustomMessageException blahThrows = assertThrows(CustomMessageException.class, ()
                            -> Parser.parseUserCommand("blah").execute(storage, taskList),
                    "Expected parser to throw, but it didn't");

            assertTrue(blahThrows.getMessage().contains("OOPS!!! I'm sorry, but I don't know what that "
                    + "means :-("));
        }
    }

    /**
     * Class that tests commands when there is previous user data
     */
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class UserDataExists {
        /**
         * Deletes the file that was created.
         */
        @AfterAll
        void tearDown() {
            ParserTest.deleteStorageFiles();
        }

        /**
         * Tests TODO.
         *
         * @throws CustomMessageException for improper input
         */
        @Test
        public void parseAddTaskCommand_validTasks_addsTasks() throws CustomMessageException {
            TaskList taskListForTest = new TaskList();
            assertEquals("Got it. I've added this task:\n"
                    + "    [T][ ] borrow book\n"
                    + "Now you have 1 task in the list.", Parser.parseUserCommand(
                    "todo borrow book").execute(storage, taskListForTest));
            assertEquals("Got it. I've added this task:\n"
                            + "    [D][ ] return book (by: 24 Aug 2022 14:00)\n"
                            + "Now you have 2 tasks in the list.",
                    Parser.parseUserCommand("deadline return book /by 2022-08-24 14:00")
                            .execute(storage, taskListForTest));
            assertEquals("Got it. I've added this task:\n"
                            + "    [E][ ] project meeting (at: 31 Dec 2023 23:59)\n"
                            + "Now you have 3 tasks in the list.",
                    Parser.parseUserCommand("event project meeting /at 2023-12-31 23:59")
                            .execute(storage, taskListForTest));
        }

        /**
         * Tests MARK.
         *
         * @throws CustomMessageException for improper input
         */
        @Test
        public void parseMarkCommand_validMark_marksTask() throws CustomMessageException {
            TaskList taskListForTest = new TaskList();
            Parser.parseUserCommand("todo borrow book").execute(storage, taskListForTest);
            assertEquals("Nice! I've marked this task as done:\n"
                            + "    [T][X] borrow book",
                    Parser.parseUserCommand("mark 1").execute(storage, taskListForTest));
        }

        /**
         * Tests UNMARK.
         *
         * @throws CustomMessageException for improper input
         */
        @Test
        public void parseUnmarkCommand_validUnmark_unmarksTask() throws CustomMessageException {
            TaskList taskListForTest = new TaskList();
            Parser.parseUserCommand("todo borrow book").execute(storage, taskListForTest);
            assertEquals("OK, I've marked this task as not done yet:\n"
                            + "    [T][ ] borrow book",
                    Parser.parseUserCommand("unmark 1").execute(storage, taskListForTest));
        }

        /**
         * Tests DELETE.
         *
         * @throws CustomMessageException for improper input
         */
        @Test
        public void parseDeleteCommand_validDelete_deletesTask() throws CustomMessageException {
            TaskList taskListForTest = new TaskList();
            Parser.parseUserCommand("deadline return book /by 2022-08-24 14:00").execute(storage, taskListForTest);
            assertEquals("Noted. I've removed this task:\n"
                            + "    [D][ ] return book (by: 24 Aug 2022 14:00)\n"
                            + "Now you have 0 tasks in the list.",
                    Parser.parseUserCommand("delete 1")
                            .execute(storage, taskListForTest));
        }
        /**
         * Tests UNDO.
         *
         * @throws CustomMessageException for improper input
         */
        @Test
        public void parseUndoCommand_validUndo_undoesTask() throws CustomMessageException {
            TaskList taskListForTest = new TaskList();
            Parser.parseUserCommand("todo borrow book").execute(storage, taskListForTest);
            assertEquals("Previous command successfully undone",
                    Parser.parseUserCommand("undo").execute(storage, taskListForTest));
        }

        /**
         * Tests TODO.
         *
         * @throws CustomMessageException for improper input
         */
        @Test
        public void parseByeCommand_validBye_showsByeMessage() throws CustomMessageException {
            TaskList taskListForTest = new TaskList();
            assertEquals("      Bye. This doesn't have to be the end!\n",
                    Parser.parseUserCommand("bye").execute(storage, taskListForTest));
        }

        /**
         * Tests LIST.
         *
         * @throws CustomMessageException for improper input
         */
        @Test
        public void parseListCommand_validList_displayList() throws CustomMessageException {
            TaskList taskListForTest = new TaskList();
            Parser.parseUserCommand("todo borrow book").execute(storage, taskListForTest);
            Parser.parseUserCommand("deadline return book /by 2022-08-24 14:00").execute(storage, taskListForTest);
            Parser.parseUserCommand("event project meeting /at 2023-12-31 23:59").execute(storage, taskListForTest);
            assertEquals("Here are the tasks in your list:\n"
                    + "  1. [T][ ] borrow book\n"
                    + "  2. [D][ ] return book (by: 24 Aug 2022 14:00)\n"
                    + "  3. [E][ ] project meeting (at: 31 Dec 2023 23:59)",
                    Parser.parseUserCommand("list").execute(storage,
                    taskListForTest));
        }

        /**
         * Tests FIND.
         *
         * @throws CustomMessageException for improper input
         */
        @Test
        public void parseFindCommand_validFind_displayResults() throws CustomMessageException {
            TaskList taskListForTest = new TaskList();
            Parser.parseUserCommand("todo borrow book").execute(storage, taskListForTest);
            Parser.parseUserCommand("deadline return book /by 2022-08-24 14:00").execute(storage, taskListForTest);
            Parser.parseUserCommand("event project meeting /at 2023-12-31 23:59").execute(storage, taskListForTest);
            assertEquals("Here are the matching tasks in your list:\n"
                            + "  1. [T][ ] borrow book\n"
                            + "  2. [D][ ] return book (by: 24 Aug 2022 14:00)",
                    Parser.parseUserCommand("find book").execute(storage, taskListForTest));
        }
    }

    /**
     * Delete generated files
     */
    public static void deleteStorageFiles() {
        final File file = new File(Storage.FILE_PATH);
        final File file2 = new File(Storage.PREVIOUS_TASKS_FILE_PATH);
        if (file.exists() && !file.delete()) {
            throw new RuntimeException("Could not delete current tasks file");
        }
        if (file2.exists() && !file2.delete()) {
            throw new RuntimeException("Could not delete previous tasks file");
        }
    }
}
