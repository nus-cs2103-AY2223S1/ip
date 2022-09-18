package duke.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import duke.command.*;
import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class ParserTest {
    private static LocalDate date = LocalDate.now();
    private static Storage storage = new Storage("./test/parsertest.txt");
    private static TaskList taskList;
    static {
        storage.clear();
        taskList = new TaskList(storage);
        taskList.add(new ToDo("todo"));
        taskList.add(new Deadline("deadline", date));
        taskList.add(new Event("event", date));
    }

    @Test
    public void parse() {
        Parser parser = new Parser(ParserTest.taskList);

        //test bye
        assertTrue(parser.parse("bye") instanceof ByeCommand);

        //test mark
        assertTrue(parser.parse("mark 2") instanceof MarkCommand);

        //test unmark
        assertTrue(parser.parse("unmark 2") instanceof UnmarkCommand);

        //test delete
        assertTrue(parser.parse("delete 2") instanceof DeleteCommand);

        //test todo
        assertTrue(parser.parse("todo desc") instanceof ToDoCommand);

        //test deadline
        assertTrue(parser.parse("deadline desc /by 1/1/22") instanceof DeadlineCommand);

        //test event
        assertTrue(parser.parse("event desc /at 1/1/22") instanceof EventCommand);

        //test find
        assertTrue(parser.parse("find desc") instanceof FindCommand);

    }

    @Test
    public void parse_badInput_printMessage() {
        Parser parser = new Parser(ParserTest.taskList);

        //non existent task
        assertEquals("I'm sorry, but the task number you have inputted does not exist or is invalid.",
                parser.parse("mark 5").get());

        assertEquals("I'm sorry, but the task number you have inputted does not exist or is invalid.",
                parser.parse("unmark 5").get());

        //no description
        assertEquals("I'm sorry, but the description cannot be empty.",
                parser.parse("todo").get());

        assertEquals("I'm sorry, but the description cannot be empty.",
                parser.parse("deadline /by 12/2/20").get());

        assertEquals("I'm sorry, but the description cannot be empty.",
                parser.parse("event /at 12/2/20").get());

        //no token
        assertEquals("I'm sorry, but you are mising the \"/by\" or \"/at\" token.",
                parser.parse("deadline").get());

        assertEquals("I'm sorry, but you are mising the \"/by\" or \"/at\" token.",
                parser.parse("deadline abc").get());

        assertEquals("I'm sorry, but you are mising the \"/by\" or \"/at\" token.",
                parser.parse("event").get());

        assertEquals("I'm sorry, but you are mising the \"/by\" or \"/at\" token.",
                parser.parse("event abc").get());

        //no or invalid date
        assertEquals("I'm sorry, but the date specified is either invalid or empty.",
                parser.parse("deadline desc /by").get());

        assertEquals("I'm sorry, but the date specified is either invalid or empty.",
                parser.parse("deadline desc /by 1abc2").get());

        assertEquals("I'm sorry, but the date specified is either invalid or empty.",
                parser.parse("deadline desc /by 2/2/").get());

        assertEquals("I'm sorry, but the date specified is either invalid or empty.",
                parser.parse("event desc /at").get());

        assertEquals("I'm sorry, but the date specified is either invalid or empty.",
                parser.parse("event desc /at 1abc2").get());

        assertEquals("I'm sorry, but the date specified is either invalid or empty.",
                parser.parse("event desc /at 2/2/").get());

        //giberish
        assertEquals("I'm sorry, but I don't know what that means.",
                parser.parse("adkadnk").get());

    }

}
