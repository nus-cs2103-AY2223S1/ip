package duke;
import duke.command.*;
import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    void testParse() {
        try {
            Command parseByeTest = Parser.parse("bye");
            ExitCommand parseByeExpected = new ExitCommand();

            Command parseListTest = Parser.parse("list");
            ListCommand parseListExpected = new ListCommand();

            Command parseFindTest = Parser.parse("find test");
            FindCommand parseFindExpected = new FindCommand("test");

            Command parseToDoTest = Parser.parse("todo test");
            ToDo toDo = new ToDo("test");
            AddCommand parseToDoExpected = new AddCommand(toDo);

            Command parseDeadlineTest = Parser.parse("deadline " +
                    "test /by 2000-10-10 23:00");
            Deadline deadline = new Deadline("test", " 2000-10-10 23:00");
            AddCommand parseDeadlineExpected = new AddCommand(deadline);

            Command parseEventTest = Parser.parse("event " +
                    "test /at 2000-10-10 23:00");
            Event event = new Event("test", " 2000-10-10 23:00");
            AddCommand parseEventExpected = new AddCommand(event);

            Command parseMarkEventTest = Parser.parse("mark 0");
            MarkStatusCommand parseMarkEventExpected =
                    new MarkStatusCommand(0);

            Command parseUnMarkEventTest = Parser.parse("unmark 0");
            UnmarkStatusCommand parseUnMarkEventExpected =
                    new UnmarkStatusCommand(0);

            Command parseDeleteTest = Parser.parse("delete 0");
            DeleteCommand parseDeleteExpected = new DeleteCommand(0);

            assertEquals(parseByeTest.equals(parseByeExpected),
                    true, "parse bye command");
            assertEquals(parseListTest.equals(parseListExpected),
                    true, "parse list command");
            assertEquals(parseFindTest.equals(parseFindExpected),
                    true, "parse find command");
            assertEquals(parseToDoTest.equals(parseToDoExpected),
                    true, "parse todo command");
            assertEquals(parseDeadlineTest.equals(parseDeadlineExpected),
                    true, "parse deadline command");
            assertEquals(parseEventTest.equals(parseEventExpected),
                    true, "parse event command");
            assertEquals(parseMarkEventTest.equals(parseMarkEventExpected),
                    true, "parse mark task command");
            assertEquals(parseUnMarkEventTest.equals(parseUnMarkEventExpected),
                    true, "parse unmark task command");
            assertEquals(parseDeleteTest.equals(parseDeleteExpected),
                    true, "parse delete task command");

        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
