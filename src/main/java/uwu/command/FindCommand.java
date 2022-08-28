package uwu.command;

import uwu.Storage;

import uwu.task.TaskList;

import uwu.Ui;


/**
 * Finds tasks based on keyword.
 */
public class FindCommand extends Command {
    /** The user input. */
    private String userCommand;

    /** The keyword the user is searching for in the tasks. */
    private String keyword;

    /** A TaskList containing the tasks with the keywords. */
    private TaskList matchedTasks;

    /**
     * Constructor for FindCommand object.
     *
     * @param userCommand The user input.
     */
    public FindCommand(String userCommand) {
        this.userCommand = userCommand;
        String[] taskData = userCommand.split(" ", 2);
        this.keyword = taskData[1].trim();
        this.matchedTasks = new TaskList();
    }

    /**
     * Executes the find command.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out UwuBot's response.
     * @param storage The task list that is stored in the user's hard disk.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int tasksLength = tasks.size();

        for (int i = 0; i < tasksLength; i++) {
            if (tasks.get(i).toString().contains(keyword)) {
                matchedTasks.add(tasks.get(i));
            }
        }

        ui.findTask(matchedTasks);
    }

    /**
     * Returns whether FindCommand exits the program.
     *
     * @return false as FindCommand does not exit the program.
     */
    public boolean isExit() {
        return false;
    }
}
