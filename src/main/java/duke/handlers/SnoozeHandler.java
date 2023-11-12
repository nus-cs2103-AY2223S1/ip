package duke.handlers;

import duke.models.Task;

import duke.exceptions.DukeException;
import duke.models.TaskList;

public class SnoozeHandler {
    public static String getResponse(TaskList taskList, String input) throws DukeException {
        if (input.length() <= 1) {
            throw new DukeException("Please enter in the valid format: snooze taskNo/yyyy-mm-dd");
        }
        String[] updatedTask = input.split("/", 2);
        assert updatedTask.length == 2: "Invalid input";
        try {
            int taskNo = Integer.parseInt(updatedTask[0]) - 1;
            String newDate = updatedTask[1];
            Task task = taskList.get(taskNo);
            task.snooze(newDate);
            return ("Got it! I've snoozed the task!");
        } catch (IndexOutOfBoundsException e) {
            int taskListSize = taskList.size();
            return (String.format("List size is %s. Please enter a valid input."
                    , taskListSize));
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
