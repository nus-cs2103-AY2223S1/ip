import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.Storage;
import duke.command.ViewScheduleCommand;
import duke.exception.DukeException;
import duke.gui.Response;
import duke.gui.ResponseType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;

public class ViewScheduleCommandTest {
    @Test
    public void executeViewSchedule_noDateSpecifiedHasTask_success() throws DukeException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data", "duke.txt");

        // to do task not completed should be on schedule
        ToDo toDoNotCompleted = new ToDo("incomplete");
        // to do task completed should not be on schedule
        ToDo toDoCompleted = new ToDo("completed", true, LocalDateTime.now());
        // deadline task not completed with a due date that's over should be on schedule
        Deadline deadlineNotCompletedOverdue = new Deadline("overdue", LocalDateTime.now().minusDays(2));
        // deadline task completed with a due date that's over should not be on schedule
        Deadline deadlineCompletedPastDue = new Deadline("completed past due",
                LocalDateTime.now().minusDays(2), true, LocalDateTime.now());
        // deadline task completed with a due date on the queried date should still be on schedule
        Deadline deadlineCompletedDueCurrent = new Deadline("completed due present",
                LocalDateTime.now(), true, LocalDateTime.now());
        // event task that's over but not marked done should be on schedule
        Event eventOverNotDone = new Event("overdue", LocalDateTime.now().minusDays(2),
                LocalDateTime.now().minusDays(2));
        // event task that's in the future and not done should not be on schedule
        Event eventNotOverNotDone = new Event("in future", LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(2));
        // event task that's happening now should be on schedule
        Event eventHappeningNow = new Event("now", LocalDateTime.now().minusDays(2),
                LocalDateTime.now().plusDays(2), true, LocalDateTime.now());

        taskList.addTask(toDoNotCompleted);
        taskList.addTask(toDoCompleted);
        taskList.addTask(deadlineNotCompletedOverdue);
        taskList.addTask(deadlineCompletedPastDue);
        taskList.addTask(deadlineCompletedDueCurrent);
        taskList.addTask(eventOverNotDone);
        taskList.addTask(eventNotOverNotDone);
        taskList.addTask(eventHappeningNow);

        ViewScheduleCommand viewScheduleCommand = new ViewScheduleCommand("");
        Response<TaskList> viewScheduleResponse = viewScheduleCommand.execute(taskList, storage);
        String expectedResponseMessage = String.format("let's see... you have 5 tasks on your schedule on %s,"
                + " let's get to work! [_(`▿´)_]", LocalDate.now());

        assertEquals(ResponseType.LIST, viewScheduleResponse.getResponseType());
        assertEquals(expectedResponseMessage, viewScheduleResponse.getResponseMessage());

        TaskList responseTaskList = viewScheduleResponse.getResponseObject();
        assertEquals(5, responseTaskList.size());
        assertEquals(toDoNotCompleted, responseTaskList.getTask(1));
        assertEquals(deadlineNotCompletedOverdue, responseTaskList.getTask(2));
        assertEquals(deadlineCompletedDueCurrent, responseTaskList.getTask(3));
        assertEquals(eventOverNotDone, responseTaskList.getTask(4));
        assertEquals(eventHappeningNow, responseTaskList.getTask(5));
    }
}
