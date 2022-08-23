package utils;

import duke.Duke;
import exceptions.DukeException;

public class Parser {

    /**
     * Decision tree for Duke to process, store and echo text input from the user.
     * @param s The entire string entered by the user, line-by-line.
     * @param arr An array of strings (words) obtained from splitting the above string.
     * @throws DukeException A custom exception for handling errors unique to Duke.
     */
    public static void decide(String s, String[] arr, TaskList taskList, Storage storage) throws DukeException {
        if (arr.length == 0) {
            return;
        } else {
            int i;
            switch (arr[0]) {
            case "mark":
                if (arr.length <= 1) {
                    throw new DukeException("Error. Please enter an argument after \"mark\".");
                }
                try {
                    i = Integer.parseInt(arr[1]) - 1;
                    if (i >= 0 && i < taskList.getSize()) {
                        taskList.markTaskAsDone(i);
                        storage.save(taskList);
                        Ui.sendMessage("Nice! I've marked this task as done:\n"
                                + "\t  " + taskList.getTaskAsString(i));
                    } else {
                        Ui.sendMessage("Please enter an integer within range.");
                    }
                } catch (NumberFormatException e) {
                    Ui.sendMessage("Please enter an integer id after \"mark\"");
                }
                break;
            case "unmark":
                if (arr.length <= 1) {
                    throw new DukeException("Error. Please enter an argument after \"unmark\".");
                }
                try {
                    i = Integer.parseInt(arr[1]) - 1;
                    if (i >= 0 && i < taskList.getSize()) {
                        taskList.markTaskAsUndone(i);
                        storage.save(taskList);
                        Ui.sendMessage("OK! I've marked this task as not done yet:\n"
                                + "\t  " + taskList.getTaskAsString(i));
                    } else {
                        Ui.sendMessage("Please enter an integer within range.");
                    }
                } catch (NumberFormatException e) {
                    Ui.sendMessage("Please enter an integer id after \"ummark\"");
                }
                break;
            case "delete":
                try {
                    if (arr.length <= 1) {
                        throw new DukeException("Error. Please enter an argument after \"delete\".");
                    }
                    i = Integer.parseInt(arr[1]) - 1;
                    if (i >= 0 && i < taskList.getSize()) {
                        taskList.delete(i);
                        storage.save(taskList);
                    } else {
                        Ui.sendMessage("Please enter an integer within range.");
                    }
                } catch (NumberFormatException e) {
                    Ui.sendMessage("Please enter an integer id after \"delete\"");
                }
                break;
            case "todo":
                if (arr.length == 1) {
                    throw new DukeException("Error. The description of a todo cannot be empty.");
                }
                String todo = s.substring(4).trim();
                taskList.add(todo, Duke.TaskType.TODO, "");
                storage.save(taskList);
                break;
            case "deadline":
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
                break;
            case "event":
                String[] eventAt = s.substring(5).trim().split("/at");
                if (eventAt.length <= 1) {
                    throw new DukeException("Error. The description and time of an event\n\tshould be separated"
                            + " by a \"/at\".");
                }
                String event = eventAt[0].trim();
                String at = eventAt[1].trim();
                taskList.add(event, Duke.TaskType.EVENT, at);
                storage.save(taskList);
                break;
            default:
                throw new DukeException("Error. Sorry, but I don't know what that means.");
            }
        }
    }
}
