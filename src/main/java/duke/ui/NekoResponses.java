package duke.ui;

import duke.enums.Command;
import duke.enums.SecondaryCommand;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The {@code CliUi} class contains string outputs used throughout the application for Cli.
 */
public class NekoResponses {

    private static final String NAME = "Neko Neko";

    private static final String CATCH_PHRASE = "Meow Meow! \n";

    /**
     * Returns a string of the messages to be seen when the application is started.
     * It contains an introduction before showing a
     * {@link #showHelp() list of valid instructions}
     *
     * @return a string of the messages to be seen when the application is started.
     */
    public String startPrompt() {
        return String.format("Hi from %s!\n%s", NAME, showHelp());
    }

    /**
     * Returns a string of the messages that serves as instructions of how to use Duke.
     *
     * @return a string of the messages that serves as instructions of how to use Duke.
     */
    public String showHelp() {
        return CATCH_PHRASE + "What can I do for you?\n" +
                String.format("- %s (task name) \n", Command.TODO.getValue()) +
                String.format("- %s (task name) %s (date) \n",
                        Command.DEADLINE.getValue(), SecondaryCommand.BY.getValue()) +
                String.format("- %s (task name) %s (date) \n",
                        Command.EVENT.getValue(), SecondaryCommand.AT.getValue()) +
                String.format("- %s\n", Command.LIST.getValue()) +
                String.format("- %s (value)\n", Command.FIND.getValue()) +
                String.format("- %s (index)\n", Command.CHECK.getValue()) +
                String.format("- %s (index)\n", Command.UNCHECK.getValue()) +
                String.format("- %s (index)\n", Command.DELETE.getValue()) +
                String.format("- %s \n", Command.HELP.getValue()) +
                String.format("- %s \n", Command.BYE.getValue()) +
                "\nEXAMPLES: \n" +
                String.format("%s cut hair \n", Command.TODO.getValue()) +
                String.format("%s cut hair %s 2022-09-11 \n",
                        Command.DEADLINE.getValue(), SecondaryCommand.BY.getValue()) +
                String.format("%s cut hair %s 2022-09-11 \n",
                        Command.EVENT.getValue(), SecondaryCommand.AT.getValue()) +
                String.format("%s\n", Command.LIST.getValue()) +
                String.format("%s cut\n", Command.FIND.getValue()) +
                String.format("%s 1\n", Command.CHECK.getValue()) +
                String.format("%s 1\n", Command.UNCHECK.getValue()) +
                String.format("%s 1\n", Command.DELETE.getValue()) +
                String.format("%s \n", Command.HELP.getValue()) +
                String.format("%s \n", Command.BYE.getValue());
    }

    /**
     * Returns a string of a messages to hint the user of the help command.
     *
     * @return a string of a messages to hint the user of the help command.
     */
    public String hintUserOfHelpCommand() {
        return String.format("%s Consider using the 'help' command by sending the word 'help' to me.", CATCH_PHRASE);
    }

    /**
     * Returns a string of the messages to be seen when the application is started.
     *
     * @return a string of the messages to be seen when the application is started.
     */
    public String listValidDateFormats() {
        return String.format("%s These are the following accepted formats: \n 1) yyyy-mm-dd", CATCH_PHRASE);
    }

    /**
     * Returns a string of the messages to be seen when the application is started.
     *
     * @return a string of the messages to be seen when the application is started.
     */
    public String markDone(String taskName) {
        return String.format("%sNice! I have marked (%s) as done!", CATCH_PHRASE, taskName);
    }

    /**
     * Returns a string of the message after marking a task as undone.
     *
     * @return a string of the message after marking a task as undone.
     */
    public String markUndone(String taskName) {
        return String.format("%sNice! I have marked (%s) as undone!", CATCH_PHRASE, taskName);
    }

    /**
     * Returns a string of all the tasks in the list.
     *
     * @param taskList task list that contains tasks to be displayed.
     * @return a string of all the tasks in the list.
     */
    public String listTasks(TaskList taskList) {
        StringBuilder output = new StringBuilder();
        output.append("Current Tasking\n");
        for (int i = 0; i < taskList.getNumberOfTask(); i++) {
            output.append(String.format("%d) %s\n", i + 1, taskList.getTask(i)));
        }
        output.append("Number of tasking: ").append(taskList.getNumberOfTask());
        output.append("\n" + CATCH_PHRASE);
        return output.toString();
    }

    /**
     * Returns a string of all tasks in the list based on a search.
     *
     * @param taskList task list that contains tasks to be displayed.
     * @param search   contains the string we are interested in.
     * @return a string of all tasks in the list based on a search.
     */
    public String findTasks(TaskList taskList, String search) {
        StringBuilder output = new StringBuilder();
        int count = 0;
        output.append(CATCH_PHRASE + "\n");
        output.append("Search Results\n");
        for (int i = 0; i < taskList.getNumberOfTask(); i++) {
            Task task = taskList.getTask(i);
            if (task.getTaskName().contains(search)) {
                output.append(String.format("%d) %s\n", i + 1, task));
                count += 1;
            }
        }
        output.append("Number of tasking: ").append(count);
        return output.toString();
    }

    /**
     * Returns a string of the message after marking adding a task.
     *
     * @param task the task that was added.
     * @return a string of the message after marking adding a task.
     */
    public String addTask(Task task) {
        return String.format("%sSuccessfully added: %s", CATCH_PHRASE, task.getTaskName());
    }

    /**
     * Returns a string of the message after marking deleting a task.
     *
     * @param task the task that was deleted.
     * @return a string of the message after marking deleting a task.
     */
    public String deleteTask(Task task) {
        return String.format("%sSuccessfully deleted: %s", CATCH_PHRASE, task.getTaskName());
    }

    /**
     * Returns a string of the message that should be displayed before closing the application.
     *
     * @return a string of the message that should be displayed before closing the application.
     */
    public String endPrompt() {
        return String.format("%sGoodbye", CATCH_PHRASE);
    }

    /**
     * Returns a string of the success message when the tasks are successfully loaded.
     *
     * @return a string of the success message when the tasks are successfully loaded.
     */
    public String loadTaskSuccessfully() {
        return String.format("%sTasks loaded successfully!", CATCH_PHRASE);
    }

    /**
     * Returns a string of the success message when a file failed to load.
     *
     * @return a string of the success message when a file failed to load.
     */
    public String loadFileFailed() {
        return String.format("%s There seems to be an issue with accessing the data file. Will clear the current list",
                CATCH_PHRASE);
    }

    /**
     * Returns a string of the success message when the task failed to load.
     *
     * @return a string of the success message when the task failed to load.
     */
    public String loadTaskFailed() {
        return String.format("%s There seems to be an issue with format of the data file. Will clear the current list",
                CATCH_PHRASE);
    }

}
