package seedu.duke;

import java.util.ArrayList;

/**
 * Represents a legal Event Command operation that can be performed by the user.
 */
public class EventCommand extends Command {

    public EventCommand() {

    }

    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage, String input) throws DukeException {
        ArrayList<Task> tasks = tasklist.getTasks();
        try {

            if (input.length() == 5) {
                throw new DukeException("The description of an event cannot be empty");
            }
            if (input.indexOf('/') == -1) {
                throw new DukeException(
                    "The date of the event should be input\n with the following format: /at YYYY-MM-DD hhmm");
            }
            String[] split = input.substring(6).split("/");
            if (split[1].length() < 4) {
                throw new DukeException(
                    "The date of the event should be input\n with the following format: /at YYYY-MM-DD hhmm");
            }
            Task task = new Event(split[0], split[1].substring(3));
            String output = addTask(tasks, task, ui, "");
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        } finally {
            storage.save(tasks);
        }
    }

    /**
     * Returns a String about the number of tasks in the list.
     *
     * @param tasks ArrayList of tasks.
     * @param task An object containing its description and tag and whether it is done.
     * @param ui Ui of the application.
     * @param output An intermediate string to be returned.
     * @return a string to be displayed in GUI when task is added.
     */
    private String addTask(ArrayList<Task> tasks, Task task, Ui ui, String output) {
        tasks.add(task);
        ui.add(task);
        return output += "Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks")
            + " in the list.";
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof EventCommand);
    }
}
