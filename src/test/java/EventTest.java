import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Event;

public class EventTest {
    @Test
    public void instantiateEventTask_properDescriptionAndDates_success() throws DukeException {
        LocalDateTime eventStartTime = LocalDateTime.now();
        LocalDateTime eventEndTime = LocalDateTime.now().plusHours(1);
        Event event = new Event("description", eventStartTime, eventEndTime);
        DateTimeFormatter dayDateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy HH:mm");
        String expectedEventString = String.format("[E][ ] description (at: %s to %s)",
                    eventStartTime.format(dayDateTimeFormatter), eventEndTime.format(dayDateTimeFormatter));
        assertEquals(expectedEventString, event.toString());
    }

    @Test
    public void instantiateEventTask_sameStartEndDateTimes_success() throws DukeException {
        LocalDateTime eventDateTime = LocalDateTime.now();
        Event event = new Event("description", eventDateTime, eventDateTime);
        DateTimeFormatter dayDateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy HH:mm");
        String expectedEventString = String.format("[E][ ] description (at: %s to %s)",
                eventDateTime.format(dayDateTimeFormatter), eventDateTime.format(dayDateTimeFormatter));
        assertEquals(expectedEventString, event.toString());
    }

    @Test
    public void instantiateEventTask_startAfterEndDate_exceptionThrown() {
        LocalDateTime eventStartTime = LocalDateTime.now();
        LocalDateTime eventEndTime = LocalDateTime.now().minusHours(1);
        try {
            new Event("description", eventStartTime, eventEndTime);
        } catch (DukeException e) {
            String expectedErrorMsg = String.format("Start datetime %s cannot be after end datetime %s",
                                        eventStartTime, eventEndTime);
            assertEquals(expectedErrorMsg, e.getMessage());
        }
    }

    @Test
    public void constructEventTask_validCommand_success() throws DukeException {
        Event event = Event.construct("description 123 /at 24/03/2022 5AM /to 17/06/22 18:00");
        assertEquals("[E][ ] description 123 (at: Thursday, 24 Mar 2022 05:00"
                + " to Friday, 17 Jun 2022 18:00)", event.toString());
    }

    @Test
    public void editEventTask_validCommand_success() throws DukeException {
        LocalDateTime eventStartTime = LocalDateTime.now();
        LocalDateTime eventEndTime = LocalDateTime.now().plusHours(1);
        Event event = new Event("description", eventStartTime, eventEndTime);
        event.edit("description 123 /at 24/03/2022 5AM /to 17/06/22 18:00");
        assertEquals("[E][ ] description 123 (at: Thursday, 24 Mar 2022 05:00"
                + " to Friday, 17 Jun 2022 18:00)", event.toString());
    }

    @Test
    public void editEventTask_startAfterEndDatetime_exceptionThrown() {
        try {
            LocalDateTime eventStartTime = LocalDateTime.now();
            LocalDateTime eventEndTime = LocalDateTime.now().plusHours(1);
            Event event = new Event("description", eventStartTime, eventEndTime);
            event.edit("description 123 /at 17/06/22 18:00 /to 24/03/2022 5AM");
        } catch (DukeException e) {
            assertEquals("Start datetime 2022-06-17T18:00 cannot be after end datetime 2022-03-24T05:00",
                    e.getMessage());
        }
    }

    @Test
    public void editEventTask_missingDescription_exceptionThrown() {
        try {
            LocalDateTime eventStartTime = LocalDateTime.now();
            LocalDateTime eventEndTime = LocalDateTime.now().plusHours(1);
            Event event = new Event("description", eventStartTime, eventEndTime);
            event.edit("/at 17/06/22 18:00 /to 24/03/2022 5AM");
        } catch (DukeException e) {
            String expectedErrorMessage = "hmm are you trying to edit an event?"
                    + " make sure the command is in the format: edit {description} /at"
                    + " {start} /to {end}";
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }

    @Test
    public void saveEventTaskStringFormat_success() throws DukeException {
        LocalDateTime eventStartTime = LocalDateTime.now();
        LocalDateTime eventEndTime = LocalDateTime.now().plusHours(1);
        Event event = new Event("read book pages 123-125", eventStartTime,
                eventEndTime, false, null);
        String expectedSavedFormat = String.format("E | N | read book pages 123-125 | %s | %s | null",
                eventStartTime, eventEndTime);
        assertEquals(expectedSavedFormat, event.toSaveFormat());
    }
}
