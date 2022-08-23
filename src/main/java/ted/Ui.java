package ted;

import ted.exception.TedException;
import ted.task.Task;
import ted.task.TaskList;

import java.util.Scanner;

public class Ui {

    private static final String GREETING = "##################################################\n" + "||                                              ||\n" + "||                Hello! I'm Ted                ||\n" + "||            What can I do for you?            ||\n" + "||                                              ||\n" + "##################################################\n";

    private static final String INPUT_PREFIX = "> ";

    public void showGreeting() {
        output(GREETING);
    }

    private Scanner scanner = new Scanner(System.in);

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

    public void showAddedTaskSuccess(TaskList tasks) {
        output(String.format("Got it. I've added this task:\n"
                + "%s\n"
                + "Now you have %d tasks in the list.\n", tasks.last().toString(), tasks.size()));
    }

    public void showDeletedTaskSuccess(TaskList tasks, Task deletedTask) {
        output(String.format("Noted. I've removed this task:\n"
                + "%s\n"
                + "Now you have %d tasks in the list.\n", deletedTask, tasks.size()));
    }

    public void showTaskLoadSuccess(int tasksCount) {
        outputLine(String.format("Loaded %d tasks from saved file.\n", tasksCount));
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

    public void exit() {
        this.scanner.close();
        outputLine("Bye. Hope to see you again!");
    }
}
