import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskType;

public class TaskTypeTest {
    @Test
    public void parseSavedTaskType_toDoTask_success() throws DukeException {
        TaskType parsedTaskType = TaskType.readSavedTaskType('T');
        assertEquals(TaskType.TODO, parsedTaskType);
    }

    @Test
    public void parseSavedTaskType_eventTask_success() throws DukeException {
        TaskType parsedTaskType = TaskType.readSavedTaskType('E');
        assertEquals(TaskType.EVENT, parsedTaskType);
    }

    @Test
    public void parseSavedTaskType_deadlineTask_success() throws DukeException {
        TaskType parsedTaskType = TaskType.readSavedTaskType('D');
        assertEquals(TaskType.DEADLINE, parsedTaskType);
    }

    @Test
    public void validateCommand_validToDoCommand_success() throws DukeException {
        Task toDo = TaskType.TODO.validateCommand("todo description");
        assertEquals("[T][ ] description", toDo.toString());
    }

    @Test
    public void validateCommand_validDeadlineCommand_success() throws DukeException {
        Task deadline = TaskType.DEADLINE.validateCommand("deadline description /by 10/10/22 3pm");
        assertEquals("[D][ ] description (by: Monday, 10 Oct 2022 15:00)", deadline.toString());
    }

    @Test
    public void validateCommand_validEventCommand_success() throws DukeException {
        Task deadline = TaskType.EVENT.validateCommand("event description /at 10/10/22 3pm /to 11/11/22 3pm");
        assertEquals("[E][ ] description (at: Monday, 10 Oct 2022 15:00 to"
                + " Friday, 11 Nov 2022 15:00)", deadline.toString());
    }

    @Test
    public void parsedSavedFormat_validCompletedToDoSaved_success() throws DukeException {
        LocalDateTime completedDate = LocalDateTime.of(2022, 9, 9, 13, 50);
        String savedFormat = String.format("T | Y | description abc | %s", completedDate);
        Task toDo = TaskType.TODO.parseSavedFormat(savedFormat);
        assertEquals("[T][X] description abc", toDo.toString());
        assertEquals(completedDate, toDo.getCompletionDateTime());
        assertEquals(true, toDo.isCompleted());
    }

    @Test
    public void parsedSavedFormat_validNotCompletedToDoSaved_success() throws DukeException {
        String savedFormat = "T | N | description abc | null";
        Task toDo = TaskType.TODO.parseSavedFormat(savedFormat);
        assertEquals("[T][ ] description abc", toDo.toString());
        assertEquals(null, toDo.getCompletionDateTime());
        assertEquals(false, toDo.isCompleted());
    }

    @Test
    public void parsedSavedFormat_validCompletedDeadlineSaved_success() throws DukeException {
        LocalDateTime dueDate = LocalDateTime.of(2022, 9, 9, 13, 00);
        LocalDateTime completedDate = LocalDateTime.of(2022, 9, 9, 13, 50);
        String savedFormat = String.format("D | Y | description abc | %s | %s", dueDate, completedDate);
        Task deadline = TaskType.DEADLINE.parseSavedFormat(savedFormat);
        assertEquals("[D][X] description abc (by: Friday, 09 Sep 2022 13:00)", deadline.toString());
        assertEquals(completedDate, deadline.getCompletionDateTime());
        assertEquals(true, deadline.isCompleted());
    }

    @Test
    public void parsedSavedFormat_validNotCompletedDeadlineSaved_success() throws DukeException {
        LocalDateTime dueDate = LocalDateTime.of(2022, 9, 9, 13, 00);
        String savedFormat = String.format("D | N | description abc | %s | null", dueDate);
        Task deadline = TaskType.DEADLINE.parseSavedFormat(savedFormat);
        assertEquals("[D][ ] description abc (by: Friday, 09 Sep 2022 13:00)", deadline.toString());
        assertEquals(null, deadline.getCompletionDateTime());
        assertEquals(false, deadline.isCompleted());
    }

    @Test
    public void parsedSavedFormat_validCompletedEventSaved_success() throws DukeException {
        LocalDateTime eventDateTime = LocalDateTime.of(2022, 9, 9, 13, 00);
        LocalDateTime completedDate = LocalDateTime.of(2022, 9, 9, 13, 50);
        String savedFormat = String.format("E | Y | description abc | %s | %s | %s",
                eventDateTime, eventDateTime, completedDate);
        Task event = TaskType.EVENT.parseSavedFormat(savedFormat);
        assertEquals("[E][X] description abc (at: Friday, 09 Sep 2022 13:00 to"
                + " Friday, 09 Sep 2022 13:00)", event.toString());
        assertEquals(completedDate, event.getCompletionDateTime());
        assertEquals(true, event.isCompleted());
    }

    @Test
    public void parsedSavedFormat_validNotCompletedEventSaved_success() throws DukeException {
        LocalDateTime eventDateTime = LocalDateTime.of(2022, 9, 9, 13, 00);
        String savedFormat = String.format("E | N | description abc | %s | %s | null",
                eventDateTime, eventDateTime);
        Task event = TaskType.EVENT.parseSavedFormat(savedFormat);
        assertEquals("[E][ ] description abc (at: Friday, 09 Sep 2022 13:00 to"
                + " Friday, 09 Sep 2022 13:00)", event.toString());
        assertEquals(null, event.getCompletionDateTime());
        assertEquals(false, event.isCompleted());
    }

    @Test
    public void parsedSavedFormat_invalidNotCompletedEventSavedWithCompletionDate_success() throws DukeException {
        LocalDateTime eventDateTime = LocalDateTime.of(2022, 9, 9, 13, 00);
        String savedFormat = String.format("E | N | description abc | %s | %s | %s",
                eventDateTime, eventDateTime, eventDateTime);
        Task event = TaskType.EVENT.parseSavedFormat(savedFormat);
        assertEquals("[E][ ] description abc (at: Friday, 09 Sep 2022 13:00 to"
                + " Friday, 09 Sep 2022 13:00)", event.toString());
        assertEquals(null, event.getCompletionDateTime());
        assertEquals(false, event.isCompleted());
    }
}
