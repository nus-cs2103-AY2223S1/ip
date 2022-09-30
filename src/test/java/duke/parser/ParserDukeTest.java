package duke.parser;

import duke.listobjects.Deadline;
import duke.listobjects.Event;
import duke.listobjects.ToDo;
import duke.tasklist.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserDukeTest {

    @Test
    public void testValidEventAddition(){
        try {
            ParserDuke parser = new ParserDuke("event tutorial/2022-10-10 18:00 19:00");
            TaskList list = new TaskList();
            Event event = new Event(" tutorial", 0, "2022-10-10 18:00 19:00");
            String expected = "Another moment to mark... \n" + event + "\n"
                    + "You now have 1 tasks to do!\n" + "\n";
            assertEquals(expected, parser.parseEvent(list));
            assertEquals(1, list.getListLength());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testValidDeadlineAddition(){
        try {
            ParserDuke parser = new ParserDuke("deadline iP/2022-12-12 16:00");
            TaskList list = new TaskList();
            Deadline deadline = new Deadline("iP", 0, "2022-12-12 16:00");
            String expected = "Mark this on your calendar! \n"
                    + "[D][ ]  iP (by: Dec 12 2022 at 16:00:00)" + "\n"
                    + "You now have 1 tasks to do!\n" + "\n";
            assertEquals(expected, parser.parseDeadline(list));
            assertEquals(1, list.getListLength());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testValidTodoAddition(){
        try {
            ParserDuke parser = new ParserDuke("todo eat pizza");
            TaskList list = new TaskList();
            ToDo todo = new ToDo(" eat pizza", 0);
            String expected = "'Tis a new sky for you to scale! Here! \n" + todo + "\n"
                    + "You now have 1 tasks  to do!\n" ;
            assertEquals(expected, parser.parseTask(list));
            assertEquals(1, list.getListLength());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testEmptyTaskDescription() {
        try{
            ParserDuke parser1 = new ParserDuke("todo");
            TaskList list = new TaskList();
            parser1.parseTask(list);
        } catch (Exception e){
            String expected = "Duke Aemon detected no task from the user. Use the `help` command to see all " +
                    "commands supported.";
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void testIncorrectArgumentNumber(){
        try{
            ParserDuke parser = new ParserDuke("deadline iP/2022-12-12");
            TaskList list = new TaskList();
            parser.parseDeadline(list);
        } catch (Exception e){
            String expected = "The folly of youth to cheat Time! Specify date and time "
                    + "to add events and deadlines...";
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void testIncorrectDateFormat(){
        ParserDuke parser = new ParserDuke("event party/10-10-2003 200 200");
        TaskList list = new TaskList();
        String expected = "Write all dates in the format of yyyy-MM-dd and "
                + "times in the format of HH:mm";
        try {
            assertEquals(expected, parser.parseEvent(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEmptyListCmd(){
        TaskList list = new TaskList();
        ParserDuke parser = new ParserDuke("list");
        String expected = "Your pages are yet blank. But the White Book was not written in a day...";
        assertEquals(expected, parser.parseListCmd(list));
    }

    @Test
    public void testDateFormatChecker(){
        try {
            ParserDuke parser = new ParserDuke("");
            String incorrectDate = "Oct 10 2003";
            String incorrectTime = "2359";
            parser.checkDateTimeFormat(incorrectDate, incorrectTime);

        } catch (Exception e){
            String expected = "Write all dates in the format of yyyy-MM-dd and "
                    + "times in the format of HH:mm";
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void testValidFindRequest(){
        ParserDuke parser = new ParserDuke("find CS2103");
        TaskList list = new TaskList();
        list.handleItemAddition(new ToDo("Study for CS2103", 1));
        list.handleItemAddition(new ToDo("Study for CS2100", 0));
        list.handleItemAddition(new ToDo("do MA2001 hw", 10));
        try {
            String expected = "Here is what you are looking for!\n" + "1. [T][X] Study for CS2103\n" + "\n";
            assertEquals(expected, parser.parseFind(list));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testInvalidFindRequest(){
        ParserDuke parser = new ParserDuke("find CS2040S");
        TaskList list = new TaskList();
        list.handleItemAddition(new ToDo("Study for CS2103", 1));
        list.handleItemAddition(new ToDo("Study for CS2100", 0));
        list.handleItemAddition(new ToDo("do MA2001 hw", 10));
        try {
            String expected = "There is no such item in the list, my friend!";
            assertEquals(expected, parser.parseFind(list));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
