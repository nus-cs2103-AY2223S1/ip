package ted;

import java.util.Scanner;

import ted.exception.TedException;
import ted.task.Task;
import ted.task.TaskList;

/**
 * Ui is use for terminal interaction with user
 */
public class Ui {

    private static final String GREETING = "##################################################\n"
            + "||                                              ||\n"
            + "||                Hello! I'm Ted                ||\n"
            + "||            What can I do for you?            ||\n"
            + "||                                              ||\n"
            + "##################################################\n";

    private static final String INPUT_PREFIX = "> ";

    /**
     * Scanner to scan user input
     */
    private Scanner scanner = new Scanner(System.in);

    public void showGreeting() {
        output(GREETING);
    }

    /**
     * Prompt input from user
     * @return user input
     */
    public String promptInput() {
        output(INPUT_PREFIX);
        scanner.hasNextLine();
        return scanner.nextLine();
    }

    public void output(String message) {
        System.out.print(message);
    }

    public void outputLine(String message) {
        output(message + "\n");
    }

    /**
     * Show success message when task is added
     * @param tasks all tasks in task list
     */
    public void showAddedTaskSuccess(TaskList tasks) {
        output(String.format("Got it. I've added this task:\n"
                + "%s\n"
                + "Now you have %d tasks in the list.\n", tasks.last().toString(), tasks.size()));
    }

    /**
     * Show success message when task is deleted
     * @param tasks all task in task list
     * @param deletedTask task that has been deleted
     */
    public void showDeletedTaskSuccess(TaskList tasks, Task deletedTask) {
        output(String.format("Noted. I've removed this task:\n"
                + "%s\n"
                + "Now you have %d tasks in the list.\n", deletedTask, tasks.size()));
    }

    public void showTaskLoadSuccess(int tasksCount) {
        outputLine(String.format("Loaded %d tasks from saved file.", tasksCount));
    }

    public void showTaskLoadError() {
        outputLine("Error while loading tasks: saved file's encoding incorrect.");
    }

    public void showTaskSavingError(Exception e) {
        outputLine("Error while saving tasks: " + e.getMessage());
    }

    public void showInputError(TedException e) {
        outputLine(e.getMessage());
    }

    public void showUnknownCommandError() {
        outputLine("I'm sorry. I don't understand what that means.");
    }

    /**
     * Exit the ui
     */
    public void exit() {
        this.scanner.close();
        outputLine("Bye. Hope to see you again!");
    }
}
