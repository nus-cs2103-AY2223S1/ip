package utils;

import duke.Duke;
import exceptions.DukeException;

/**
 * Handles the logic for which action to take based on the user's input.
 */
public class Parser {

    /**
     * Decides the actions for Duke to take.
     * @param s The entire string entered by the user, line-by-line.
     * @param arr An array of strings (words) obtained from splitting the above string.
     * @throws DukeException A custom exception for handling errors unique to Duke.
     */
    public static String decide(String s, String[] arr, TaskList taskList, Storage storage) throws DukeException {
        assert(s.length() != 0);
        assert(taskList != null);
        assert(storage != null);

        if (arr.length == 0) {
            return "Please enter a valid command.";
        } else {
            String command = arr[0];
            switch (command) {
                case "find":
                    return handleFindCase(s, arr, taskList);
                case "mark":
                    return handleMarkCase(arr, taskList, storage);
                case "unmark":
                    return handleUnmarkCase(arr, taskList, storage);
                case "delete":
                    return handleDeleteCase(arr, taskList, storage);
                case "todo":
                    return handleTodoCase(s, arr, taskList, storage);
                case "deadline":
                    return handleDeadlineCase(s, taskList, storage);
                case "event":
                    return handleEventCase(s, taskList, storage);
                case "postpone":
                    return handlePostponeCase(s, taskList, storage);
                default:
                    throw new DukeException("Error. Sorry, but I don't know what that means.");
            }
        }
    }

    private static String handlePostponeCase(String s, TaskList taskList, Storage storage) throws DukeException {
        String[] content = s.substring(8).trim().split("/to");
        if (content.length <= 1) {
            throw new DukeException("Usage: postpone {id} /to {new date}");
        }
        try {
            int taskId = Integer.parseInt(content[0].trim()) - 1;
            String to = content[1].trim();

            if (!taskList.checkIfTaskIsDeadline(taskId)) {
                throw new DukeException("Please enter a task ID which corresponds to a deadline.");
            }
            if (!to.trim().matches("(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2})(\\d{2})")) {
                throw new DukeException("Invalid datetime entered.");
            }
            if (taskList.checkIfInvalidDate(taskId, to)) {
                throw new DukeException("Date to postpone task should not be earlier than existing date.");
            }
            taskList.updateDeadlineDueDate(taskId, to);
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid integer for the task ID.");
        }
        return "Task has been postponed.";
    }

    private static String handleFindCase(String s, String[] arr, TaskList taskList) throws DukeException {
        if (arr.length <= 1) {
            throw new DukeException("Error. Please enter a suitable description for your search.");
        }
        String desc = s.substring(4).trim().toLowerCase();
        return taskList.getAllOccurrencesOf(desc);
    }

    private static String handleMarkCase(String[] arr, TaskList taskList, Storage storage) throws DukeException {
        int i;
        if (arr.length <= 1) {
            throw new DukeException("Error. Please enter an argument after \"mark\".");
        }
        try {
            i = Integer.parseInt(arr[1]) - 1;
            if (i >= 0 && i < taskList.getSize()) {
                taskList.markTaskAsDone(i);
                storage.save(taskList);
                return "Nice! I've marked this task as done:\n"
                        + "\t  " + taskList.getTaskAsString(i);
            } else {
                return "Please enter an integer within range.";
            }
        } catch (NumberFormatException e) {
            return "Please enter an integer id after \"mark\"";
        }
    }

    private static String handleUnmarkCase(String[] arr, TaskList taskList, Storage storage) throws DukeException {
        int i;
        if (arr.length <= 1) {
            throw new DukeException("Error. Please enter an argument after \"unmark\".");
        }
        try {
            i = Integer.parseInt(arr[1]) - 1;
            if (i >= 0 && i < taskList.getSize()) {
                taskList.markTaskAsUndone(i);
                storage.save(taskList);
                return "OK! I've marked this task as not done yet:\n"
                        + "\t  " + taskList.getTaskAsString(i);
            } else {
                return "Please enter an integer within range.";
            }
        } catch (NumberFormatException e) {
            return "Please enter an integer id after \"ummark\"";
        }
    }

    private static String handleDeleteCase(String[] arr, TaskList taskList, Storage storage) throws DukeException {
        int i;
        try {
            if (arr.length <= 1) {
                throw new DukeException("Error. Please enter an argument after \"delete\".");
            }
            i = Integer.parseInt(arr[1]) - 1;
            if (i >= 0 && i < taskList.getSize()) {
                taskList.delete(i);
                storage.save(taskList);
                return "Task updated in storage";
            } else {
                return "Please enter an integer within range.";
            }
        } catch (NumberFormatException e) {
            return "Please enter an integer id after \"delete\"";
        }
    }
    private static String handleTodoCase(String s, String[] arr, TaskList taskList, Storage storage) throws DukeException {
        if (arr.length == 1) {
            throw new DukeException("Error. The description of a todo cannot be empty.");
        }
        String todo = s.substring(4).trim();
        taskList.add(todo, Duke.TaskType.TODO, "");
        storage.save(taskList);
        return "Task updated in storage";
    }

    private static String handleDeadlineCase(String s, TaskList taskList, Storage storage) throws DukeException {
        String[] deadlineBy = s.substring(8).trim().split("/by");
        if (deadlineBy.length <= 1) {
            throw new DukeException("Error. The description and due date of a deadline\n\tshould be "
                    + "separated" + " by a \"/by\".");
        }
        String deadline = deadlineBy[0].trim();
        String by = deadlineBy[1].trim();

        // Regex adapted from:
        // stackoverflow.com/questions/37732/what-is-the-regex-pattern-for-datetime-2008-09-01-123545
        if (!by.trim().matches("(\\d{4})-(\\d{2})-(\\d{2}) (\\d{2})(\\d{2})")) {
            throw new DukeException("Invalid datetime entered.");
        }
        taskList.add(deadline, Duke.TaskType.DEADLINE, by);
        storage.save(taskList);
        return "Task updated in storage";
    }

    private static String handleEventCase(String s, TaskList taskList, Storage storage) throws DukeException {
        String[] eventAt = s.substring(5).trim().split("/at");
        if (eventAt.length <= 1) {
            throw new DukeException("Error. The description and time of an event\n\tshould be separated"
                    + " by a \"/at\".");
        }
        String event = eventAt[0].trim();
        String at = eventAt[1].trim();
        taskList.add(event, Duke.TaskType.EVENT, at);
        storage.save(taskList);
        return "Task updated in storage";
    }
}
