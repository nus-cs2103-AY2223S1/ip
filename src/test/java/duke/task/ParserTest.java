package duke.task;

import duke.command.*;
import duke.logic.Parser;
import duke.logic.Storage;
import duke.logic.TaskList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ParserTest {
    private static LocalDate date = LocalDate.now();
    private static TaskList taskList = new TaskList(new Storage("./test/parsertest.txt"));
    static {
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

    }

    @Test
    public void parse_badInput_printMessage() {
        Parser parser = new Parser(ParserTest.taskList);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        //non existent task
        parser.parse("mark 5").run();
        assertEquals(" ☹ OOPS!!! The task number you have inputted does not exist or is invalid.\n",
                outContent.toString());
        outContent.reset();

        parser.parse("unmark 5").run();
        assertEquals(" ☹ OOPS!!! The task number you have inputted does not exist or is invalid.\n",
                outContent.toString());
        outContent.reset();

        //no description
        parser.parse("todo").run();
        assertEquals(" ☹ OOPS!!! The description cannot be empty.\n",
                outContent.toString());
        outContent.reset();

        parser.parse("deadline /by 12/2/20").run();
        assertEquals(" ☹ OOPS!!! The description cannot be empty.\n",
                outContent.toString());
        outContent.reset();

        parser.parse("event /at 12/2/20").run();
        assertEquals(" ☹ OOPS!!! The description cannot be empty.\n",
                outContent.toString());
        outContent.reset();

        //no token
        parser.parse("deadline").run();
        assertEquals(" ☹ OOPS!!! You are mising the \"/by\" or \"/at\" token.\n",
                outContent.toString());
        outContent.reset();

        parser.parse("deadline abc").run();
        assertEquals(" ☹ OOPS!!! You are mising the \"/by\" or \"/at\" token.\n",
                outContent.toString());
        outContent.reset();

        parser.parse("event").run();
        assertEquals(" ☹ OOPS!!! You are mising the \"/by\" or \"/at\" token.\n",
                outContent.toString());
        outContent.reset();

        parser.parse("event abc").run();
        assertEquals(" ☹ OOPS!!! You are mising the \"/by\" or \"/at\" token.\n",
                outContent.toString());
        outContent.reset();

        //no or invalid date
        parser.parse("deadline desc /by").run();
        assertEquals(" ☹ OOPS!!! The time specified is either invalid or empty.\n",
                outContent.toString());
        outContent.reset();

        parser.parse("deadline desc /by 1abc2").run();
        assertEquals(" ☹ OOPS!!! The time specified is either invalid or empty.\n",
                outContent.toString());
        outContent.reset();

        parser.parse("deadline desc /by 2/2/").run();
        assertEquals(" ☹ OOPS!!! The time specified is either invalid or empty.\n",
                outContent.toString());
        outContent.reset();

        parser.parse("event desc /at").run();
        assertEquals(" ☹ OOPS!!! The time specified is either invalid or empty.\n",
                outContent.toString());
        outContent.reset();

        parser.parse("event desc /at 1abc2").run();
        assertEquals(" ☹ OOPS!!! The time specified is either invalid or empty.\n",
                outContent.toString());
        outContent.reset();

        parser.parse("event desc /at 2/2/").run();
        assertEquals(" ☹ OOPS!!! The time specified is either invalid or empty.\n",
                outContent.toString());
        outContent.reset();

        //giberish
        parser.parse("adkadnk").run();
        assertEquals(" ☹ OOPS!!! I'm sorry, but I don't know what that means. :-(\n",
                outContent.toString());
        outContent.reset();
    }

}
