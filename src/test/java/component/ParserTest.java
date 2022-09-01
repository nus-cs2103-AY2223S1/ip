package component;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    void parseTest() {
        String command1 = "event CS2103 /at 0900";
        String command2 = "bye";
        String command3 = "deadline PHS3116 /by 2359";
        String command4 = "asdfghjkl";
        String command5 = "unmark 2";
        String command6 = "mark 4";
        String command7 = "list";
        String command8 = "todo Do laundry";
        String command9 = "delete 10";
        assertAll(() -> assertEquals(Parser.parse(command1), Command.CREATE_EVENT), () ->
                assertEquals(Parser.parse(command2), Command.EXIT), () ->
                assertEquals(Parser.parse(command3), Command.CREATE_DEADLINE), () ->
                assertEquals(Parser.parse(command4), Command.UNKNOWN), () ->
                assertEquals(Parser.parse(command5), Command.UNMARK), () ->
                assertEquals(Parser.parse(command6), Command.MARK), () ->
                assertEquals(Parser.parse(command7), Command.LIST), () ->
                assertEquals(Parser.parse(command8), Command.CREATE_TODO), () ->
                assertEquals(Parser.parse(command9), Command.DELETE)
        );
    }

    @Test
    void getTaskIndexTest() {
        String command1 = "unmark 3";
        String command2 = "delete 1";
        String command3 = "mark 9";
        assertEquals(Parser.getTaskIndex(command1), 2);
        assertEquals(Parser.getTaskIndex(command2), 0);
        assertEquals(Parser.getTaskIndex(command3), 8);
    }

    @Test
    void parseTaskTest() {
        String command1 = "event CS2103 /at 0900";
        String command2 = "deadline PHS3116 /by 2359";
        String command3 = "todo Do laundry";
        Task task1 = Parser.parseTask(command1, "E");
        Task task2 = Parser.parseTask(command2, "D");
        Task task3 = Parser.parseTask(command3, "T");
        LocalDateTime dateTime1 = Parser.processDateTime("0900");
        LocalDateTime dateTime2 = Parser.processDateTime("2359");
        Task testTask1 = new Event(dateTime1, "CS2103");
        Task testTask2 = new Deadline(dateTime2, "PHS3116");
        Task testTask3 = new ToDo("Do laundry");
        assertEquals(task1.getDescription(), testTask1.getDescription());
        assertEquals(task1.getCode(), testTask1.getCode());
        assertEquals(task1.getStatusIcon(), testTask1.getStatusIcon());
        assertEquals(task1.printText(), testTask1.printText());
        assertEquals(task2.getDescription(), testTask2.getDescription());
        assertEquals(task2.getCode(), testTask2.getCode());
        assertEquals(task2.getStatusIcon(), testTask2.getStatusIcon());
        assertEquals(task2.printText(), testTask2.printText());
        assertEquals(task3.getDescription(), testTask3.getDescription());
        assertEquals(task3.getCode(), testTask3.getCode());
        assertEquals(task3.getStatusIcon(), testTask3.getStatusIcon());
        assertEquals(task3.printText(), testTask3.printText());
    }

    @Test
    void processDateTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime testDate = LocalDateTime.parse("2022-08-26 09:00", format);
        LocalDateTime date = Parser.processDateTime("20220826 0900");
        assertEquals(date, testDate);
    }
}
