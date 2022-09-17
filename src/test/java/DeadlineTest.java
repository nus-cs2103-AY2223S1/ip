import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Deadline;

public class DeadlineTest {
    @Test
    public void instantiateDeadlineTask_validCompletionDescriptionAndDate_success() {
        LocalDateTime dueDate = LocalDateTime.now();
        DateTimeFormatter dayDateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy HH:mm");
        String dueDateString = dueDate.format(dayDateTimeFormatter);
        Deadline deadline = new Deadline("description", dueDate, true, dueDate);
        String expectedDeadlineString = String.format("[D][X] description (by: %s)", dueDateString);
        assertEquals(deadline.toString(), expectedDeadlineString);
    }

    @Test
    public void constructDeadlineTask_validCommand_success() throws DukeException {
        Deadline deadline = Deadline.construct("description abc /by 21/06/21 6PM");
        String expectedDeadlineString = "[D][ ] description abc (by: Monday, 21 Jun 2021 18:00)";
        assertEquals(expectedDeadlineString, deadline.toString());
    }

    @Test
    public void constructDeadlineTask_missingDescription_exceptionThrown() {
        try {
            Deadline.construct("/by 21/06/21 6PM");
        } catch (DukeException e) {
            String expectedErrorMessage = "hmm are you trying to edit a deadline?"
                    + " make sure the command is in the format: deadline {description}"
                    + " /by {due date}";
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }

    @Test
    public void constructDeadlineTask_missingDueDate_exceptionThrown() {
        try {
            Deadline.construct("description abc");
        } catch (DukeException e) {
            String expectedErrorMessage = "hmm are you trying to edit a deadline?"
                    + " make sure the command is in the format: deadline {description}"
                    + " /by {due date}";
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }

    @Test
    public void checkActiveDeadline_dueDatePassedNotComplete_success() {
        // incomplete passed due date should be considered active
        Deadline deadline = new Deadline("description", LocalDateTime.now().minusDays(10));
        assertEquals(deadline.isActive(LocalDate.now().minusDays(8)), true);
    }

    @Test
    public void checkActiveDeadline_dueDateCurrentComplete_success() {
        // completed but query on due date should be considered active
        Deadline deadline = new Deadline("description", LocalDateTime.now().minusDays(1),
                true, LocalDateTime.now().minusDays(10));
        assertEquals(deadline.isActive(LocalDate.now().minusDays(1)), true);
    }

    @Test
    public void checkActiveDeadline_dueDateNotPassedNotComplete_success() {
        // incomplete not passed due date should not be considered active
        Deadline deadline = new Deadline("description", LocalDateTime.now().plusDays(10));
        assertEquals(deadline.isActive(LocalDate.now()), false);
    }

    @Test
    public void checkActiveDeadline_dueDatePassedCompleted_success() {
        // completed and due date passed should not be considered active
        Deadline deadline = new Deadline("description", LocalDateTime.now().minusDays(1),
                true, LocalDateTime.now().minusDays(10));
        assertEquals(deadline.isActive(LocalDate.now()), false);
    }

    @Test
    public void saveDeadlineTaskStringFormat_success() {
        LocalDateTime dueDate = LocalDateTime.of(2022, 10, 10, 15, 0);
        Deadline deadline = new Deadline("read book pages 123-125", dueDate);
        String expectedSavedFormat = String.format("D | N | read book pages 123-125 | %s | null", dueDate);
        assertEquals(expectedSavedFormat, deadline.toSaveFormat());
    }
}
