package Duke.Storage;

import Duke.Exception.DukeException;
import Duke.Task.Deadline;
import Duke.Task.Task;
import Duke.Task.ToDo;
import Duke.Task.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StorageStub {
    public Task loadTask(String taskStr) throws DukeException {
        try {
            String[] taskArray = taskStr.split("\\|");
            String taskTypeStr = taskArray[0];
            String isDoneStr = taskArray[1];
            String description = taskArray[2];

            Task newTask;
            switch (taskTypeStr) {
                case "T":
                    newTask = new ToDo(description);
                    break;
                case "D":
                    String byStr = taskArray[3];
                    LocalDateTime by = getDateTime(byStr);
                    newTask = new Deadline(description, by);
                    break;
                case "E":
                    String atStr = taskArray[3];
                    LocalDateTime at = getDateTime(atStr);
                    newTask = new Event(description, at);
                    break;
                default:
                    throw DukeException.readRowFromFileException(taskStr);
            }

            boolean isDone = isDoneStr.equals("X");
            if (isDone) {
                newTask.markAsDone();
            }

            return newTask;
        } catch (DukeException de) {
            throw DukeException.readRowFromFileException(
                    String.format("%s - %s", taskStr, de.toString()));
        } catch (Exception e) {
            throw DukeException.readRowFromFileException(taskStr);
        }
    }

    private LocalDateTime getDateTime(String datetimeStr) throws DukeException {
        try {
            return LocalDateTime.parse(
                    datetimeStr,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException | IllegalArgumentException e) {
            throw DukeException.taskDateTimeException("Date time should be in the format yyyy-MM-dd HHmm!");
        }
    }
}