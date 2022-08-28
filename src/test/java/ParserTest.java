import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.Deadline;
import duke.DukeException;
import duke.Parser;
import duke.TaskList;
import duke.Todo;

/**
 * To unit test the Parser class.
 */
public class ParserTest {

    /**
     * Tests the conditional logic when encountering a todo input.
     *
     * @throws DukeException not thrown.
     */
    @Test
    public void testParseTodo() throws DukeException {
        TaskList tasklist = new TaskList();
        Parser parser = new Parser(tasklist);
        parser.parse("todo testtodo");
        Todo todo = new Todo("testtodo");
        assertEquals(todo.toString(), tasklist.taskListToArray()[0].toString());
    }

    /**
     * Tests the conditional logic when encountering a mark input.
     *
     * @throws DukeException not thrown.
     */
    @Test
    public void testMark() throws DukeException {
        TaskList tasklist = new TaskList();
        Deadline deadline = new Deadline("testdeadline", "2012-12-12 12:12");
        deadline.markAsDone();
        tasklist.add(deadline);
        Parser parser = new Parser(tasklist);
        parser.parse("deadline testdeadline /by 2012-12-12 12:12");
        parser.parse("mark 2");
        assertEquals(tasklist.taskListToArray()[0].toString(), tasklist.taskListToArray()[1].toString());
    }

    /**
     * Tests the conditional logic when encountering an invalid input.
     */
    @Test
    public void testInvalidInput() {
        Parser parser = new Parser(new TaskList());
        Throwable exception = assertThrows(Throwable.class, () -> {
            parser.parse("test");
        });
        assertEquals("I'm sorry, but I don't know what that means :(" , exception.getMessage());
    }

}
