package duke.parser;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ParserTest {
    @Test
    public void parse_blankString_exceptionThrown() {
        Exception e1 = assertThrows(DukeException.class, () -> Parser.parse(""));
        Exception e2 = assertThrows(DukeException.class, () -> Parser.parse("   "));
        
        String expected = "  Somehow you're even more unbearable when you're silent.";

        assertAll(() -> assertEquals(expected, e1.getMessage()),
                () -> assertEquals(expected, e2.getMessage())
        );
    }

    @Test
    public void parse_pipeCharacter_exceptionThrown() {
        Exception e1 = assertThrows(DukeException.class,
                () -> Parser.parse("deadline spanish | homework /by 31/12/2022"));

        Exception e2 = assertThrows(DukeException.class,
                () -> Parser.parse("event birthday | party /at 31/12/2022")
        );

        Exception e3 = assertThrows(DukeException.class, () -> Parser.parse("todo read| a book"));

        String expected = "  '|' characters are not allowed in the task description.";
        String actual1 = e1.getMessage();
        String actual2 = e2.getMessage();
        String actual3 = e3.getMessage();

        assertAll(() -> assertEquals(expected, actual1),
                () -> assertEquals(expected, actual2),
                () -> assertEquals(expected, actual3)
        );
    }
    
    @Test
    public void parse_unknownCommand_exceptionThrown() {
        Exception e1 = assertThrows(DukeException.class, () -> Parser.parse("random word"));
        Exception e2 = assertThrows(DukeException.class, () -> Parser.parse("list sss"));
        Exception e3 = assertThrows(DukeException.class, () -> Parser.parse("bye asda"));
        
        String msg = " is not a valid command.\n  If you want my help, the least "
                + "you could do is say something I understand.";
        String expected1 = "  'random word'" + msg;
        String expected2 = "  'list sss'" + msg;
        String expected3 = "  'bye asda'" + msg;
        
        assertAll(() -> assertEquals(expected1, e1.getMessage()),
                () -> assertEquals(expected2, e2.getMessage()),
                () -> assertEquals(expected3, e3.getMessage())
        );
    }
    
    @Test
    public void parse_wrongNumberFormat_exceptionThrown() {
        Exception e1 = assertThrows(DukeException.class, () -> Parser.parse("delete asdf"));
        Exception e2 = assertThrows(DukeException.class, () -> Parser.parse("mark a2a"));
        Exception e3 = assertThrows(DukeException.class, () -> Parser.parse("unmark _3df"));
        
        String msg = "  Do you need me to teach you what a number is?\n  '";

        String expected1 = msg + "asdf' is not a number.";
        String expected2 = msg + "a2a' is not a number.";
        String expected3 = msg + "_3df' is not a number.";

        assertAll(() -> assertEquals(expected1, e1.getMessage()),
                () -> assertEquals(expected2, e2.getMessage()),
                () -> assertEquals(expected3, e3.getMessage())
        );
    }
    
    @Test
    public void parse_missingTaskNumber_exceptionThrown() {
        Exception e1 = assertThrows(DukeException.class, () -> Parser.parse("mark  "));
        Exception e2 = assertThrows(DukeException.class, () -> Parser.parse("unmark  "));
        Exception e3 = assertThrows(DukeException.class, () -> Parser.parse("delete  "));
        Exception e4 = assertThrows(DukeException.class, () -> Parser.parse("mark"));
        Exception e5 = assertThrows(DukeException.class, () -> Parser.parse("unmark"));
        Exception e6 = assertThrows(DukeException.class, () -> Parser.parse("delete"));
        
        String msg = "  Enter a task number, nitwit.\n  Type \"";
        String expected1 = msg + "mark <task number>\" to mark a task as complete.";
        String expected2 = msg + "unmark <task number>\" to mark a task as incomplete.";
        String expected3 = msg + "delete <task number>\" to delete a task.";

        assertAll(() -> assertEquals(expected1, e1.getMessage()),
                () -> assertEquals(expected2, e2.getMessage()),
                () -> assertEquals(expected3, e3.getMessage()),
                () -> assertEquals(expected1, e4.getMessage()),
                () -> assertEquals(expected2, e5.getMessage()),
                () -> assertEquals(expected3, e6.getMessage())
        );
    }

    @Test
    public void parse_blankToDoDescription_exceptionThrown() {
        Exception e1 = assertThrows(DukeException.class, () -> Parser.parse("todo"));
        Exception e2 = assertThrows(DukeException.class, () -> Parser.parse("todo "));
        Exception e3 = assertThrows(DukeException.class, () -> Parser.parse("todo  "));
        
        String expected = "  It seems you've invented a way to do nothing. Typical...\n"
                + "  Type \"todo <description>\" to add a new todo.";

        assertAll(() -> assertEquals(expected, e1.getMessage()),
                () -> assertEquals(expected, e2.getMessage()),
                () -> assertEquals(expected, e3.getMessage())
        );
    }

    @Test
    public void parse_blankEventDescription_exceptionThrown() {
        Exception e1 = assertThrows(DukeException.class, () -> Parser.parse("event"));
        Exception e2 = assertThrows(DukeException.class, () -> Parser.parse("event "));
        Exception e3 = assertThrows(DukeException.class, () -> Parser.parse("event  "));
        Exception e4 = assertThrows(DukeException.class, () -> Parser.parse("event /at 1/1/2022 1630"));
        Exception e5 = assertThrows(DukeException.class, () -> Parser.parse("event  /at 1/1/2022 1630"));
        Exception e6 = assertThrows(DukeException.class, () -> Parser.parse("event /at Sunday"));

        String expected = "  It seems you've invented a way to do nothing. Typical...\n"
                + "  Type \"event <description> /at <date> <time>[optional]\" to add a new event.";

        assertAll(() -> assertEquals(expected, e1.getMessage()),
                () -> assertEquals(expected, e2.getMessage()),
                () -> assertEquals(expected, e3.getMessage()),
                () -> assertEquals(expected, e4.getMessage()),
                () -> assertEquals(expected, e5.getMessage()),
                () -> assertEquals(expected, e6.getMessage())
        );
    }

    @Test
    public void parse_blankDeadlineDescription_exceptionThrown() {
        Exception e1 = assertThrows(DukeException.class, () -> Parser.parse("deadline"));
        Exception e2 = assertThrows(DukeException.class, () -> Parser.parse("deadline "));
        Exception e3 = assertThrows(DukeException.class, () -> Parser.parse("deadline  "));
        Exception e4 = assertThrows(DukeException.class, () -> Parser.parse("deadline /by 1/1/2022 1630"));
        Exception e5 = assertThrows(DukeException.class, () -> Parser.parse("deadline  /by 1/1/2022 1630"));
        Exception e6 = assertThrows(DukeException.class, () -> Parser.parse("deadline /by Sunday"));

        String expected = "  It seems you've invented a way to do nothing. Typical...\n"
                + "  Type \"deadline <description> /by <date> <time>[optional]\" to add a new deadline.";

        assertAll(() -> assertEquals(expected, e1.getMessage()),
                () -> assertEquals(expected, e2.getMessage()),
                () -> assertEquals(expected, e3.getMessage()),
                () -> assertEquals(expected, e4.getMessage()),
                () -> assertEquals(expected, e5.getMessage()),
                () -> assertEquals(expected, e6.getMessage())
        );
    }
    

    @Test
    public void parse_missingDeadlineDateTime_exceptionThrown() {
        Exception e1 = assertThrows(DukeException.class, () -> Parser.parse("deadline / by 1/4/2022 0815"));
        Exception e2 = assertThrows(DukeException.class, () -> Parser.parse("deadline spanish homework"));
        Exception e3 = assertThrows(DukeException.class, () -> Parser.parse("deadline spanish homework "));
        Exception e4 = assertThrows(DukeException.class, () -> Parser.parse("deadline spanish homework  "));
        Exception e5 = assertThrows(DukeException.class, () -> Parser.parse("deadline spanish homework/by"));
        Exception e6 = assertThrows(DukeException.class,
                () -> Parser.parse("deadline spanish homework /by")
        );
        
        Exception e7 = assertThrows(DukeException.class,
                () -> Parser.parse("deadline spanish homework /by ")
        );

        String expected = "  You do realise deadlines and events usually have a time or date, right?\n"
                + "  Type \"deadline <description> /by <date> <time>[optional]\" to add a new deadline.";

        assertAll(() -> assertEquals(expected, e1.getMessage()),
                () -> assertEquals(expected, e2.getMessage()),
                () -> assertEquals(expected, e3.getMessage()),
                () -> assertEquals(expected, e4.getMessage()),
                () -> assertEquals(expected, e5.getMessage()),
                () -> assertEquals(expected, e6.getMessage()),
                () -> assertEquals(expected, e7.getMessage())
        );
    }

    @Test
    public void parse_missingEventDateTime_exceptionThrown() {
        Exception e1 = assertThrows(DukeException.class, () -> Parser.parse("event / at 1/4/2022 0815"));
        Exception e2 = assertThrows(DukeException.class, () -> Parser.parse("event project meeting"));
        Exception e3 = assertThrows(DukeException.class, () -> Parser.parse("event project meeting "));
        Exception e4 = assertThrows(DukeException.class, () -> Parser.parse("event project meeting  "));
        Exception e5 = assertThrows(DukeException.class, () -> Parser.parse("event project meeting/at"));
        Exception e6 = assertThrows(DukeException.class,
                () -> Parser.parse("event project meeting /at")
        );

        Exception e7 = assertThrows(DukeException.class,
                () -> Parser.parse("event project meeting /at ")
        );
        
        String expected = "  You do realise deadlines and events usually have a time or date, right?\n"
                + "  Type \"event <description> /at <date> <time>[optional]\" to add a new event.";

        assertAll(() -> assertEquals(expected, e1.getMessage()),
                () -> assertEquals(expected, e2.getMessage()),
                () -> assertEquals(expected, e3.getMessage()),
                () -> assertEquals(expected, e4.getMessage()),
                () -> assertEquals(expected, e5.getMessage()),
                () -> assertEquals(expected, e6.getMessage()),
                () -> assertEquals(expected, e7.getMessage())
        );
    }
    
    @Test
    public void convertStringToDateTime_acceptedFormat_success() throws DukeException {
        LocalDateTime dt1 = Parser.convertStringToDateTime("1/4/2022 0435");
        LocalDateTime dt2 = Parser.convertStringToDateTime("1/04/2022 0435");
        LocalDateTime dt3 = Parser.convertStringToDateTime("01/4/2022 0435");
        LocalDateTime dt4 = Parser.convertStringToDateTime("01/04/2022 0435");
        
        String expected = "2022-04-01T04:35";
        
        assertAll(() -> assertEquals(expected, dt1.toString()),
                () -> assertEquals(expected, dt2.toString()),
                () -> assertEquals(expected, dt3.toString()),
                () -> assertEquals(expected, dt4.toString())
        );
    }
    
    @Test
    public void convertStringToDate_acceptedFormat_success() throws DukeException {
        LocalDate d1 = Parser.convertStringToDate("1/4/2022");
        LocalDate d2 = Parser.convertStringToDate("1/04/2022");
        LocalDate d3 = Parser.convertStringToDate("01/4/2022");
        LocalDate d4 = Parser.convertStringToDate("01/04/2022");

        String expected = "2022-04-01";

        assertAll(() -> assertEquals(expected, d1.toString()),
                () -> assertEquals(expected, d2.toString()),
                () -> assertEquals(expected, d3.toString()),
                () -> assertEquals(expected, d4.toString())
        );
    }

    @Test
    public void convertStringToDateTime_invalidDateTime_exceptionThrown() {
        Exception e1 = assertThrows(DukeException.class,
                () -> Parser.convertStringToDateTime("1-4-2022 4 PM")
        );

        Exception e2 = assertThrows(DukeException.class,
                () -> Parser.convertStringToDateTime("1/4/22 1600")
        );

        Exception e3 = assertThrows(DukeException.class,
                () -> Parser.convertStringToDateTime("1/4/2022  1600")
        );

        Exception e4 = assertThrows(DukeException.class,
                () -> Parser.convertStringToDateTime("1 Apr 2022 1600")
        );

        Exception e5 = assertThrows(DukeException.class,
                () -> Parser.convertStringToDateTime("1/4/2022 2500")
        );

        Exception e6 = assertThrows(DukeException.class,
                () -> Parser.convertStringToDateTime("32/4/2022 1600")
        );

        Exception e7 = assertThrows(DukeException.class,
                () -> Parser.convertStringToDateTime("1/13/2022 1600")
        );
        
        String msg1 = "  Invalid date or time: '";
        String msg2 = "'\n  Please enter a date and time with the following format: 'dd/mm/yyyy HHMM'";
        
        String expected1 = msg1 + "1-4-2022 4 PM" + msg2;
        String expected2 = msg1 + "1/4/22 1600" + msg2;
        String expected3 = msg1 + "1/4/2022  1600" + msg2;
        String expected4 = msg1 + "1 Apr 2022 1600" + msg2;
        String expected5 = msg1 + "1/4/2022 2500" + msg2;
        String expected6 = msg1 + "32/4/2022 1600" + msg2;
        String expected7 = msg1 + "1/13/2022 1600" + msg2;

        assertAll(() -> assertEquals(expected1, e1.getMessage()),
                () -> assertEquals(expected2, e2.getMessage()),
                () -> assertEquals(expected3, e3.getMessage()),
                () -> assertEquals(expected4, e4.getMessage()),
                () -> assertEquals(expected5, e5.getMessage()),
                () -> assertEquals(expected6, e6.getMessage()),
                () -> assertEquals(expected7, e7.getMessage())
        );
    }

    @Test
    public void convertStringToDate_invalidDate_exceptionThrown() {
        Exception e1 = assertThrows(DukeException.class, () -> Parser.convertStringToDate("1-4-2022"));
        Exception e2 = assertThrows(DukeException.class, () -> Parser.convertStringToDate("1/4/22"));
        Exception e3 = assertThrows(DukeException.class, () -> Parser.convertStringToDate("32/4/2022"));
        Exception e4 = assertThrows(DukeException.class, () -> Parser.convertStringToDate("1/13/2022"));
        Exception e5 = assertThrows(DukeException.class, () -> Parser.convertStringToDate("1 Apr 2022"));

        String msg1 = "  Invalid date: '";
        String msg2 = "'\n  Please enter a date with the following format: 'dd/mm/yyyy'";

        String expected1 = msg1 + "1-4-2022" + msg2;
        String expected2 = msg1 + "1/4/22" + msg2;
        String expected3 = msg1 + "32/4/2022" + msg2;
        String expected4 = msg1 + "1/13/2022" + msg2;
        String expected5 = msg1 + "1 Apr 2022" + msg2;

        assertAll(() -> assertEquals(expected1, e1.getMessage()),
                () -> assertEquals(expected2, e2.getMessage()),
                () -> assertEquals(expected3, e3.getMessage()),
                () -> assertEquals(expected4, e4.getMessage()),
                () -> assertEquals(expected5, e5.getMessage())
        );
    }
    
    @Test
    public void testParseSavedTask() {
        String[] p1 = Parser.parseSavedTask("T | 0 | buy pen refill");
        String[] p2 = Parser.parseSavedTask("E | 1 | birthday party | 1/1/2022");
        String[] p3 = Parser.parseSavedTask("D | 0 | project meeting | 1/1/2022 1630");
        
        assertAll(() -> assertEquals(p1.length, 3),
                () -> assertEquals(p1[0], "T"),
                () -> assertEquals(p1[1], "0"),
                () -> assertEquals(p1[2], "buy pen refill"),
                () -> assertEquals(p2.length, 4),
                () -> assertEquals(p2[0], "E"),
                () -> assertEquals(p2[1], "1"),
                () -> assertEquals(p2[2], "birthday party"),
                () -> assertEquals(p2[3], "1/1/2022"),
                () -> assertEquals(p3.length, 4),
                () -> assertEquals(p3[0], "D"),
                () -> assertEquals(p3[1], "0"),
                () -> assertEquals(p3[2], "project meeting"),
                () -> assertEquals(p3[3], "1/1/2022 1630")
        );
    }
    
}
