package mort.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import mort.command.CommandWord;
import mort.exception.MortException;
import mort.task.Deadline;
import mort.task.Event;
import mort.task.ToDo;

public class UiTest {
    private final LocalDateTime dateTime1 = LocalDateTime.parse("16/9/2022 1200",
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    private final LocalDateTime dateTime2 = LocalDateTime.parse("1/10/2022 1000",
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    private final LocalDate date = dateTime1.toLocalDate();
    private final ToDo todo = new ToDo("buy 6 apples & chicken");
    private final Deadline deadline = new Deadline("spanish video project", dateTime1);
    private final Event event = new Event("CS2106 midterm", dateTime2);

    private Ui ui = new Ui();

    @Test
    public void testGetWelcomeMessage() {
        String expected = "Oh, it's you again...\nMort, begrudgingly at your service.\n"
                + "Hmph, what do you want now?";
        assertEquals(expected, ui.getWelcomeMessage());
    }

    @Test
    public void testGetExitMessage() {
        String expected = "With all due disrespect, leave me alone next time.";
        assertEquals(expected, ui.getExitMessage());
    }

    @Test
    public void testGetError() {
        MortException e = new MortException("Error message");
        String expected = "Error message";
        assertEquals(expected, ui.getError(e));
    }

    @Test
    public void getAddMessage_sizeOne() {
        String startMessage = "Seriously? Another one?\nGive me strength...\n";
        String endMessage = "\nYou have 1 task. Bummer.";
        String expected1 = startMessage + "  [T][ ] buy 6 apples & chicken" + endMessage;
        String expected2 = startMessage + "  [D][ ] spanish video project (by: 16 Sep 2022, 12:00 PM)" + endMessage;
        String expected3 = startMessage + "  [E][ ] CS2106 midterm (at: 1 Oct 2022, 10:00 AM)" + endMessage;
        assertEquals(expected1, ui.getAddMessage(todo, 1));
        assertEquals(expected2, ui.getAddMessage(deadline, 1));
        assertEquals(expected3, ui.getAddMessage(event, 1));
    }

    @Test
    public void getAddMessage_sizeMoreThanOne() {
        String startMessage = "Seriously? Another one?\nGive me strength...\n  ";
        String expected1 = startMessage + "[T][ ] buy 6 apples & chicken\nYou have 2 tasks. Bummer.";
        String expected2 = startMessage + "[D][ ] spanish video project (by: 16 Sep 2022, 12:00 PM)"
                + "\nYou have 10 tasks. Bummer.";
        String expected3 = startMessage + "[E][ ] CS2106 midterm (at: 1 Oct 2022, 10:00 AM)"
                + "\nYou have 100 tasks. Bummer.";
        assertEquals(expected1, ui.getAddMessage(todo, 2));
        assertEquals(expected2, ui.getAddMessage(deadline, 10));
        assertEquals(expected3, ui.getAddMessage(event, 100));
    }

    @Test
    public void getDeleteMessage_sizeOne() {
        String startMessage = "Good riddance, I say.\n  ";
        String endMessage = "\nYou have 1 task.";
        String expected1 = startMessage + "[T][ ] buy 6 apples & chicken" + endMessage;
        String expected2 = startMessage + "[D][ ] spanish video project (by: 16 Sep 2022, 12:00 PM)" + endMessage;
        String expected3 = startMessage + "[E][ ] CS2106 midterm (at: 1 Oct 2022, 10:00 AM)" + endMessage;
        assertEquals(expected1, ui.getDeleteMessage(todo, 1));
        assertEquals(expected2, ui.getDeleteMessage(deadline, 1));
        assertEquals(expected3, ui.getDeleteMessage(event, 1));
    }

    @Test
    public void getDeleteMessage_sizeNotOne() {
        String startMessage = "Good riddance, I say.\n  ";
        String expected1 = startMessage + "[T][ ] buy 6 apples & chicken\nYou have 2 tasks.";
        String expected2 = startMessage + "[D][ ] spanish video project (by: 16 Sep 2022, 12:00 PM)"
                + "\nYou have 0 tasks.";
        String expected3 = startMessage + "[E][ ] CS2106 midterm (at: 1 Oct 2022, 10:00 AM)"
                + "\nYou have 12 tasks.";
        assertEquals(expected1, ui.getDeleteMessage(todo, 2));
        assertEquals(expected2, ui.getDeleteMessage(deadline, 0));
        assertEquals(expected3, ui.getDeleteMessage(event, 12));
    }

    @Test
    public void getListMessage_emptyTaskList() {
        String expected = "You don't have any tasks. Make yourself useful.\n";
        assertEquals(expected, ui.getListMessage(""));
    }

    @Test
    public void getListMessage_nonEmptyTaskList() {
        String list = "1. [T][ ] buy 6 apples & chicken\n"
                + "2. [D][X] spanish video project (by: 16 Sep 2022, 12:00 PM)\n"
                + "3. [E][ ] CS2106 midterm (at: 1 Oct 2022, 10:00 AM)\n";
        String expected = "*rolls eyes*\nFine. Here are your tasks:\n" + list;
        assertEquals(expected, ui.getListMessage(list));
    }

    @Test
    public void testGetMissingTaskError() {
        String message = "\nTask number 18 does not exist.";
        String expectedDelete = "Impressive. You've figured out how to delete non-existent tasks." + message;
        String expectedMark = "Brilliant. You've asked me to mark an imaginary task as complete." + message;
        String expectedUnmark = "Brilliant. You've asked me to mark an imaginary task as incomplete." + message;
        assertEquals(expectedDelete, ui.getMissingTaskError(CommandWord.DELETE, 18));
        assertEquals(expectedMark, ui.getMissingTaskError(CommandWord.MARK, 18));
        assertEquals(expectedUnmark, ui.getMissingTaskError(CommandWord.UNMARK, 18));
    }

    @Test
    public void testGetUnknownCommandMessage() {
        String expected = "'hello' is not a valid command.\nIf you want my help, the least you could do "
                + "is say something I understand.";
        assertEquals(expected, Ui.getUnknownCommandMessage("hello"));
    }

    @Test
    public void testGetCommandHelp() {
        String expectedToDo = "Type \"todo <description>\" to add a new todo.";
        String expectedDeadline = "Type \"deadline <description> /by <date> <time>[optional]\" to add a new deadline.";
        String expectedEvent = "Type \"event <description> /at <date> <time>[optional]\" to add a new event.";
        String expectedMark = "Type \"mark <task number>\" to mark a task as complete.";
        String expectedUnmark = "Type \"unmark <task number>\" to mark a task as incomplete.";
        String expectedDelete = "Type \"delete <task number>\" to delete a task.";
        String expectedFind = "Type \"find <keyword>\" to search for a task.";
        String expectedView = "Type \"view <date>\" to view all tasks on that date.";
        assertEquals(expectedToDo, Ui.getCommandHelp(CommandWord.TODO));
        assertEquals(expectedDeadline, Ui.getCommandHelp(CommandWord.DEADLINE));
        assertEquals(expectedEvent, Ui.getCommandHelp(CommandWord.EVENT));
        assertEquals(expectedMark, Ui.getCommandHelp(CommandWord.MARK));
        assertEquals(expectedUnmark, Ui.getCommandHelp(CommandWord.UNMARK));
        assertEquals(expectedDelete, Ui.getCommandHelp(CommandWord.DELETE));
        assertEquals(expectedFind, Ui.getCommandHelp(CommandWord.FIND));
        assertEquals(expectedView, Ui.getCommandHelp(CommandWord.VIEW));
    }

    @Test
    public void getFindMessage_emptyResult() {
        String expected = "No matches found for 'he11O'. Did you have fun wasting my time?";
        assertEquals(expected, ui.getFindMessage("he11O", ""));
    }

    @Test
    public void getFindMessage_nonEmptyResult() {
        String result = "1. [T][ ] buy 6 apples & chicken\n"
                + "2. [D][X] chinese homework (by: 12 Oct 2022)\n";
        String expected = "Really? Find them yourself next time.\nHere's what I found for 'CHi':\n"
                + result;
        assertEquals(expected, ui.getFindMessage("CHi", result));
    }

    @Test
    public void getViewMessage_emptyResult() {
        String expected = "You don't have any tasks on 16 Sep 2022. Make yourself useful.\n";
        assertEquals(expected, ui.getViewMessage("", date));
    }

    @Test
    public void getViewMessage_nonEmptyResult() {
        String result = "1. [D][ ] spanish video project (by: 16 Sep 2022, 12:00 PM)\n"
                + "2. [E][X] Tom's Birthday (at: 16 Sep 2022)\n";
        String expected = "Do I have to?\nWhatever. Here are your tasks for 16 Sep 2022:\n"
                + result;
        assertEquals(expected, ui.getViewMessage(result, date));
    }

}
