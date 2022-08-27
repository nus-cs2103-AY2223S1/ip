package piggy;

import piggy.task.Deadline;
import piggy.task.Event;
import piggy.task.Task;
import piggy.task.Todo;

/**
 * Class that contains methods to help with parsing user commands.
 */
class Parser {
    /**
     * Parses a given command and prints the appropriate responses.
     *
     * @param command The command to parse.
     * @param ui The ui to use for displaying the responses.
     * @param taskList A list of existing tasks.
     * @return true if the command is to exit, false otherwise.
     */
    static boolean parse(String command, Ui ui, TaskList taskList) {
        if (command.equals("list")) {
            ui.showTaskList(taskList);
        } else if (command.matches("^mark \\d+$")) {
            Task task = taskList.get(Integer.parseInt(command.substring(5)) - 1);
            task.markDone();
            ui.showMarkAsDone(task);
        } else if (command.matches("^unmark \\d+$")) {
            Task task = taskList.get(Integer.parseInt(command.substring(7)) - 1);
            task.markNotDone();
            ui.showMarkAsNotDone(task);
        } else if (command.matches("^delete \\d+$")) {
            Task task = taskList.remove(Integer.parseInt(command.substring(7)) - 1);
            ui.showTaskRemoved(task, taskList.size());
        } else if (command.equals("bye")) {
            return true;
        } else {
            Task task;
            try {
                if (command.startsWith("todo")) {
                    if (command.length() < 6) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    task = new Todo(command.substring(5));
                } else if (command.startsWith("deadline")) {
                    if (command.length() < 10) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    } else if (!command.contains("/by")) {
                        throw new DukeException("A deadline must contain a /by");
                    }
                    String[] split = command.substring(9).split(" /by ");
                    task = new Deadline(split[0], split[1]);
                } else if (command.startsWith("event")) {
                    if (command.length() < 7) {
                        throw new DukeException("The description of an event cannot be empty.");
                    } else if (!command.contains("/at")) {
                        throw new DukeException("An event must contain an /at");
                    }
                    String[] split = command.substring(6).split(" /at ");
                    task = new Event(split[0], split[1]);
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
                taskList.add(task);
                ui.showTaskAdded(task, taskList.size());
            } catch (DukeException err) {
                ui.showDukeException(err);
            }
        }
        return false;
    }
}
