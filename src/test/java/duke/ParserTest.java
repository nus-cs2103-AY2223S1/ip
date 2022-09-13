package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import duke.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.task.TaskList;
import duke.task.ToDo;


public class ParserTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    public void initAll() {
        tasks = new TaskList(new ArrayList<>());
        ui = new Ui();
        storage = new Storage("./data/data.txt");
    }

    @Test
    public void parser_validBatchTypeDeleteToDo() {
        try {
            String input = "batchtypedelete todo";
            // Propagate tasks list
            for (int i = 0; i < 10; i++) {
                tasks.add(new ToDo("Todo Sample"));
            }
            int initialSize = tasks.size();
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            int finalSize = tasks.size();

            String expected = "Initial: 10, Final: 0";
            String output = String.format("Initial: %d, Final: %d", initialSize, finalSize);
            assertEquals(expected, output);

        } catch (DukeException e) {
            fail("Exception thrown");
        }
    }

    @Test
    public void parser_validBatchTypeDeleteDeadline() {
        try {
            String input = "batchtypedelete deadline";
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);

            assertTrue(true);

        } catch (DukeException e) {
            fail("Exception thrown");
        }
    }

    @Test
    public void parser_validBatchTypeDeleteEvent() {
        try {
            String input = "batchtypedelete event";
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);

            assertTrue(true);

        } catch (DukeException e) {
            fail("Exception thrown");
        }
    }

    @Test
    public void parser_validBatchDescDelete() {
        try {
            String input = "batchdescdelete 1";
            // Propagate tasks list
            for (int i = 0; i < 20; i++) {
                tasks.add(new ToDo(String.format("%d", i)));
            }
            int initialSize = tasks.size();
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            int finalSize = tasks.size();

            String expected = "Initial: 20, Final: 9";
            String output = String.format("Initial: %d, Final: %d", initialSize, finalSize);
            assertEquals(expected, output);

            assertTrue(true);

        } catch (DukeException e) {
            fail("Exception thrown");
        }
    }

    @Test
    public void parser_validDeadline() {
        try {
            String input = "deadline Valid Deadline /by 2009-09-12 1300";
            Command c = Parser.parse(input);

            String expected = "Got it. I've added this task:\n"
                    + "[D][ ] Valid Deadline (by: 2009 Sep 12 01:00PM)\n"
                    + "Now you have 1 tasks in the list.\n";

            assertEquals(expected, c.execute(tasks, ui, storage));
        } catch (DukeException e) {
            fail("Not suppose to throw exception");
        }
    }

    @Test
    public void parser_validEvent() {
        try {
            String input = "Event Valid Event /at 2009-09-12 1300";
            Command c = Parser.parse(input);

            String expected = "Got it. I've added this task:\n"
                    + "[E][ ] Valid Event (at: 2009 Sep 12 01:00PM)\n"
                    + "Now you have 1 tasks in the list.\n";

            assertEquals(expected, c.execute(tasks, ui, storage));
        } catch (DukeException e) {
            fail("Not suppose to throw exception");
        }
    }

    @Test
    public void parser_validToDo() {
        try {
            String input = "Todo Valid Todo";
            Command c = Parser.parse(input);

            String expected = "Got it. I've added this task:\n"
                    + "[T][ ] Valid Todo\n"
                    + "Now you have 1 tasks in the list.\n";

            assertEquals(expected, c.execute(tasks, ui, storage));
        } catch (DukeException e) {
            fail("Not suppose to throw exception");
        }
    }

    @Test
    public void parser_validMarkEffect() {
        try {
            ToDo todo = new ToDo("hehehe");
            tasks.add(todo);
            String input = "Mark 1";
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            String expected = "X";

            assertEquals(expected, todo.getStatusIcon());
        } catch (DukeException e) {
            fail("Not suppose to throw exception");
        }
    }

    @Test
    public void parser_validMarkDescription() {
        try {
            ToDo todo = new ToDo("hehehe");
            ToDo todoFinal = new ToDo("hehehe", true);
            tasks.add(todo);
            String input = "Mark 1";
            Command c = Parser.parse(input);

            String expected = ui.printMarkTask(todoFinal) + "\n";

            assertEquals(expected, c.execute(tasks, ui, storage));
        } catch (DukeException e) {
            fail("Not suppose to throw exception");
        }
    }

    @Test
    public void parser_validUnmarkEffect() {
        try {
            ToDo todo = new ToDo("hehehe", true);
            tasks.add(todo);
            String input = "Unmark 1";
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            String expected = " ";

            assertEquals(expected, todo.getStatusIcon());
        } catch (DukeException e) {
            fail("Not suppose to throw exception");
        }
    }

    @Test
    public void parser_validUnmarkDescription() {
        try {
            ToDo todo = new ToDo("hehehe", true);
            ToDo todoFinal = new ToDo("hehehe");
            tasks.add(todo);
            String input = "Unmark 1";
            Command c = Parser.parse(input);
            String expected = ui.printUnmarkTask(todoFinal) + "\n";

            assertEquals(expected, c.execute(tasks, ui, storage));
        } catch (DukeException e) {
            fail("Not suppose to throw exception");
        }
    }

    @Test
    public void parser_validDeleteEffect() {
        try {
            ToDo todo = new ToDo("hehehe", true);
            tasks.add(todo);
            String input = "delete 1";
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            int expected = 0;

            assertEquals(expected, tasks.size());
        } catch (DukeException e) {
            fail("Not suppose to throw exception");
        }
    }

    @Test
    public void parser_validDeleteDescription() {
        try {
            ToDo todo = new ToDo("hehehe", true);
            tasks.add(todo);
            String input = "delete 1";
            Command c = Parser.parse(input);

            String expected = "Noted. I've removed this task:" + "\n"
                    + "[T][X] hehehe" + "\n"
                    + "Now you have 0 tasks in the list.\n";

            assertEquals(expected, c.execute(tasks, ui, storage));
        } catch (DukeException e) {
            fail("Not suppose to throw exception");
        }
    }

    @Test
    public void parser_emptyTodo_exceptionThrown() {
        try {
            String input = "Todo";
            Command c = Parser.parse(input);
            fail("DukeEmptyToDoException not thrown");
        } catch (DukeEmptyToDoException e) {
            assertTrue(true);
        } catch (DukeException e) {
            fail("Wrong exception thrown");
        }
    }

    @Test
    public void parser_emptyDeadline_exceptionThrown() {
        try {
            String input = "Deadline";
            Command c = Parser.parse(input);
            fail("DukeEmptyDeadlineException not thrown");
        } catch (DukeEmptyDeadlineException e) {
            assertTrue(true);
        } catch (DukeException e) {
            fail("Wrong exception thrown");
        }
    }

    @Test
    public void parser_emptyEvent_exceptionThrown() {
        try {
            String input = "Event";
            Command c = Parser.parse(input);
            fail("DukeEmptyEventException not thrown");
        } catch (DukeEmptyEventException e) {
            assertTrue(true);
        } catch (DukeException e) {
            fail("Wrong exception thrown");
        }
    }

    @Test
    public void parser_invalidCommand_exceptionThrown() {
        try {
            String input = "hehe jam";
            Command c = Parser.parse(input);
            fail("DukeInvalidCommandException not thrown");
        } catch (DukeInvalidCommandException e) {
            assertTrue(true);
        } catch (DukeException e) {
            fail("Wrong exception thrown");
        }
    }

    @Test
    public void parser_invalidDeadlineDescription_exceptionThrown() {
        try {
            String input = "deadline invalid Deadline /at 2009-09-12 1300";
            Command c = Parser.parse(input);
            fail("DukeInvalidDeadlineSeparatorException not thrown");
        } catch (DukeInvalidDeadlineSeparatorException e) {
            assertTrue(true);
        } catch (DukeException e) {
            fail("Wrong exception thrown");
        }
    }

    @Test
    public void parser_invalidEventDescription_exceptionThrown() {
        try {
            String input = "Event Valid Event /by 2009-09-12 1300";
            Command c = Parser.parse(input);
            fail("DukeInvalidEventSeparatorException not thrown");
        } catch (DukeInvalidEventSeparatorException e) {
            assertTrue(true);
        } catch (DukeException e) {
            fail("Wrong exception thrown");
        }
    }

    @Test
    public void parser_invalidDeadlineDate_exceptionThrown() {
        try {
            String input = "deadline invalid Deadline /by 2009-09-122 13000";
            Command c = Parser.parse(input);
            fail("DukeInvalidTimeFormatException not thrown");
        } catch (DukeInvalidTimeFormatException e) {
            assertTrue(true);
        } catch (DukeException e) {
            fail("Wrong exception Thrown");
        }
    }

    @Test
    public void parser_invalidEventDate_exceptionThrown() {
        try {
            String input = "event invalid event /at 2009-09-122 13000";
            Command c = Parser.parse(input);
            fail("DukeInvalidTimeFormatException not thrown");
        } catch (DukeInvalidTimeFormatException e) {
            assertTrue(true);
        } catch (DukeException e) {
            fail("Wrong exception Thrown");
        }
    }

    @Test
    public void parser_noMarkIndex_exceptionThrown() {
        try {
            String input = "Mark";
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);

            fail("No Index exception not thrown");
        } catch (DukeNoIndexException e) {
            assertTrue(true);
        } catch (DukeException e) {
            fail("Wrong Exception Thrown");
        }
    }

    @Test
    public void parser_noUnmarkIndex_exceptionThrow() {
        try {
            String input = "Unmark";
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);

            fail("No Index exception not thrown");
        } catch (DukeNoIndexException e) {
            assertTrue(true);
        } catch (DukeException e) {
            fail("Wrong Exception Thrown");
        }
    }

    @Test
    public void parser_noDeleteIndex_exceptionThrown() {
        try {
            String input = "delete";
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);

            fail("No Index exception not thrown");
        } catch (DukeNoIndexException e) {
            assertTrue(true);
        } catch (DukeException e) {
            fail("Wrong Exception Thrown");
        }
    }

    @Test
    public void parser_noKeywordFind_exceptionThrown() {
        try {
            String input = "find";
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);

            fail("DukeNoKeywordException not thrown");
        } catch (DukeNoKeywordException e) {
            assertTrue(true);
        } catch (DukeException e) {
            fail("Wrong Exception Thrown");
        }
    }

    @Test
    public void parser_noKeywordBatchTypeDelete_exceptionThrown() {
        try {
            String input = "batchtypedelete";
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);

            fail("DukeNoKeywordException not thrown");
        } catch (DukeNoKeywordException e) {
            assertTrue(true);
        } catch (DukeException e) {
            fail("Wrong exception thrown");
        }
    }

    @Test
    public void parser_invalidTypeBatchTypeDelete_exceptionThrown() {
        try {
            String input = "batchtypedelete magic";
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);

            fail("DukeInvalidTypeException not thrown");
        } catch (DukeInvalidTypeException e) {
            assertTrue(true);
        } catch (DukeException e) {
            fail("Wrong exception thrown");
        }
    }

    @Test
    public void parser_invalidTypeBatchDescDelete_exceptionThrown() {
        try {
            String input = "batchdescdelete";
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);

            fail("DukeNoKeywordException not thrown");
        } catch (DukeNoKeywordException e) {
            assertTrue(true);
        } catch (DukeException e) {
            fail("Wrong exception thrown");
        }
    }

}
