package command;

import storage.Storage;
import task.TaskList;
import task.NotesList;
import ui.Ui;
import exception.DukeException;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;
import task.Notes;

/**
 * Represents an Add Command that inherits from
 * the abstract class Command.
 */

public class AddCommand extends Command {
    protected String commandLine;

    /**
     * Constructor for this AddCommand that takes in a
     * commandLine as a String.
     *
     * @param commandLine Command from the user.
     */
    public AddCommand(String commandLine) {
        this.commandLine = commandLine;
    }

    /**
     * Returns a boolean false to show that this
     * is not the last command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command depending on which type of
     * task it is and prints a string to tell the user that
     * the task has been added.
     *
     * @param taskList The ArrayList that contains all the tasks.
     * @param ui The Ui that interacts with the next command from the user.
     * @param storage The Storage used to store the tasks.
     * @throws DukeException If the description of task is empty.
     */
    @Override
    public String execute(TaskList taskList, NotesList notesList, Ui ui, Storage storage) throws DukeException {
        String response = "";
        assert !taskList.isEmpty();
        assert !notesList.isEmpty();
        if (commandLine.startsWith("todo")) {
            try {
                if (commandLine.length() <= 5) {
                    throw new DukeException("The description of a todo cannot be empty.");
                } else {
                    String actTask = commandLine.substring(5);
                    Task currTask = new Todo(actTask);
                    taskList.add(currTask);
                    storage.updateData(taskList, notesList);
                    response = "Got it. I've added this todo task:\n"
                            + " " + currTask + "\n" + "Now you have "
                            + taskList.size() + " tasks in the list.\n";
                    System.out.println(response);
                    return response;
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
                return e.toString();
            }
        } else if (commandLine.startsWith("deadline")) {
            try {
                if (commandLine.length() <= 9) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                } else {
                    if (!commandLine.contains("/")) {
                        throw new DukeException("There is no date! >:(\n");
                    } else {
                        int slashIndex = commandLine.indexOf("/");
                        String actTask = commandLine.substring(9, slashIndex - 1);
                        String taskDate = commandLine.substring(slashIndex + 4);
                        Task currTask = new Deadline(actTask, taskDate);
                        taskList.add(currTask);
                        storage.updateData(taskList, notesList);
                        response = "Got it. I've added this deadline task:\n"
                                + " " + currTask + "\n" + "Now you have "
                                + taskList.size() + " tasks in the list.\n";
                        System.out.println(response);
                        return response;
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
                return e.toString();
            }
        } else if (commandLine.startsWith("event")) {
            try {
                if (commandLine.length() <= 6) {
                    throw new DukeException("The description of a event cannot be empty.");
                } else {
                    if (!commandLine.contains("/")) {
                        throw new DukeException("There is no date! >:(");
                    } else {
                        int slashIndex = commandLine.indexOf("/");
                        String actTask = commandLine.substring(6, slashIndex - 1);
                        String taskDate = commandLine.substring(slashIndex + 4);
                        Task currTask = new Event(actTask, taskDate);
                        taskList.add(currTask);
                        storage.updateData(taskList, notesList);
                        response = "Got it. I've added this event task:\n"
                                + " " + currTask + "\n" + "Now you have "
                                + taskList.size() + " tasks in the list.\n";
                        System.out.println(response);
                        return response;
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
                return e.toString();
            }
        } else {
            try {
                if (commandLine.length() <= 6) {
                    throw new DukeException("The description of a notes cannot be empty.");
                } else {
                    String actTask = commandLine.substring(6);
                    Task currNote = new Notes(actTask);
                    notesList.add(currNote);
                    storage.updateData(taskList, notesList);
                    response = "Got it. I've added this note:\n"
                            + " " + currNote + "\n" + "Now you have "
                            + notesList.size() + " notes in the list.\n";
                    System.out.println(response);
                    return response;
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
                return e.toString();
            }
        }
    }
}
