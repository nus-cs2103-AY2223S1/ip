package Duke.ui;

import Duke.enums.Command;
import Duke.enums.SecondaryCommand;
import Duke.task.Task;
import Duke.task.TaskList;

/**
 * The {@code CliUi} class contains string outputs used throughout the application for Cli.
 */
public class DukeResponses {

    /**
     * Returns a string of the messages to be seen when the application is started.
     * It contains an introduction before showing a
     * {@link #listValidInstructions() list of valid instructions}
     *
     * @return a string of the messages to be seen when the application is started.
     */
    public String startPrompt() {
        return String.format("%s \n %s", "Hi from Yi Xian", listValidInstructions());
    }

    /**
     * Returns a string of the messages to be seen when the application is started.
     *
     * @return a string of the messages to be seen when the application is started.
     */
    public String listValidInstructions() {
        return "What can I do for you?\n" +
                String.format("- %s (task name)%n", Command.TODO.getValue()) +
                String.format("- %s (task name) %s (date) \n",
                        Command.DEADLINE.getValue(), SecondaryCommand.BY.getValue()) +
                String.format("- %s (task name) %s (date) \n",
                        Command.EVENT.getValue(), SecondaryCommand.AT.getValue()) +
                String.format("- %s\n", Command.LIST.getValue()) +
                String.format("- %s\n", Command.FIND.getValue()) +
                String.format("- %s (index)\n", Command.CHECK.getValue()) +
                String.format("- %s (index)\n", Command.UNCHECK.getValue()) +
                String.format("- %s (index)\n", Command.DELETE.getValue()) +
                String.format("- %s", Command.BYE.getValue());
    }

    /**
     * Returns a string of the messages to be seen when the application is started.
     *
     * @return a string of the messages to be seen when the application is started.
     */
    public String listValidDateFormats() {
        return "These are the following accepted formats: \n 1) yyyy-mm-dd";
    }

    /**
     * Returns a string of the messages to be seen when the application is started.
     *
     * @return a string of the messages to be seen when the application is started.
     */
    public String markDone(String taskName) {
        return "Nice! I have marked (" + taskName + ") as done!";
    }

    /**
     * Returns a string of the message after marking a task as undone.
     *
     * @return a string of the message after marking a task as undone.
     */
    public String markUndone(String taskName) {
        return "Nice! I have marked (" + taskName + ") as undone!";
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
        return String.format("Successfully added: %s", task.getTaskName());
    }

    /**
     * Returns a string of the message after marking deleting a task.
     *
     * @param task the task that was deleted.
     * @return a string of the message after marking deleting a task.
     */
    public String deleteTask(Task task) {
        return String.format("Successfully deleted: %s", task.getTaskName());
    }

    /**
     * Returns a string of the message that should be displayed before closing the application.
     *
     * @return a string of the message that should be displayed before closing the application.
     */
    public String endPrompt() {
        return "Goodbye";
    }

    /**
     * Returns a string of the success message when the tasks are successfully loaded.
     *
     * @return a string of the success message when the tasks are successfully loaded.
     */
    public String loadTaskSuccessfully() {
        return "Tasks loaded successfully!";
    }

    /**
     * Returns a string of the success message when a file failed to load.
     *
     * @return a string of the success message when a file failed to load.
     */
    public String loadFileFailed() {
        return "There seems to be an issue with accessing the data file. Will clear the current list";
    }

    /**
     * Returns a string of the success message when the task failed to load.
     *
     * @return a string of the success message when the task failed to load.
     */
    public String loadTaskFailed() {
        return "There seems to be an issue with format of the data file. Will clear the current list";
    }

}
