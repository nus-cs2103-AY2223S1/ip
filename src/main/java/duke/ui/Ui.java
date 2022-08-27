package duke.ui;

import java.util.Scanner;

import duke.task.Task;

/**
 * Ui class that handles showing the user texts.
 *
 * @author Elgin
 */
public class Ui {
    /**
     * Greet user message.
     *
     */
    public void greetUser() {
        String message = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(Ui.formatText(message));
    }

    /**
     * Bye to user (End process message).
     *
     */
    public void sayBye() {
        String message = "Bye. Hope to see you again soon!";
        System.out.println(Ui.formatText(message));
    }

    /**
     * Read user input
     *
     * @return The command that user has entered, cleaned.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);

        String userInput = sc.nextLine();

        return userInput.trim();
    }

    /**
     * Shows user that task has successfully been added, and the current number of tasks.
     *
     */
    public void showTaskAdded(int newTaskCount, Task task) {
        String addedMessage = "Got it. I've added this task:\n" + "  "
                + task + "\n" + "Now you have " + newTaskCount + " tasks in the list.";

        System.out.println(Ui.formatText(addedMessage));
    }

    /**
     * Shows user that task has successfully been deleted, and the number of tasks left.
     *
     */
    public void showTaskDeleted(int newTaskCount, String taskDescription) {
        String deletedMessage = "Noted. I've removed this task:\n  "
                + taskDescription + "\n"
                + "Now you have " + newTaskCount + " tasks in the list";

        System.out.println(Ui.formatText(deletedMessage));
    }

    /**
     * Shows user that task has successfully been marked.
     *
     */
    public void showTaskMarked(String taskDescription) {
        String markMessage = "Nice! I've marked this task as done:\n" + taskDescription;

        System.out.println(Ui.formatText(markMessage));
    }

    /**
     * Shows user that task is successfully unmarked.
     *
     */
    public void showTaskUnmarked(String taskDescription) {
        String unmarkMessage = "OK, I've marked this task as not done yet:\n" + taskDescription;

        System.out.println(Ui.formatText(unmarkMessage));
    }

    /**
     * Shows user all tasks that are stored.
     *
     * @param allTasks String representation of allTasks.
     */
    public void showAllTasks(String allTasks) {
        String tasksMessage = "Here are the tasks in your list\n" + allTasks;

        System.out.println(Ui.formatText(tasksMessage));
    }

    /**
     * File Loading Tasks Error.
     *
     */
    public void showLoadingError() {
        String errorMessage = "☹ OOPS!!! Failed to load tasks because file cannot be opened!";
        System.out.println(Ui.formatText(errorMessage));
    }

    /**
     * Shows Duke error message to user.
     *
     * @param error Error message.
     */
    public void showDukeError(String error) {
        System.out.println(Ui.formatText("☹ OOPS!!! " + error));
    }

    /**
     * Shows date parsing error (Invalid date format).
     *
     */
    public void showInvalidDateError() {
        String errorMessage = "☹ OOPS!!! Your date format has to be in the form 'yyyy-mm-dd'";
        System.out.println(Ui.formatText(errorMessage));
    }

    /**
     * Shows String to Number cast error (because user did not input a number).
     *
     */
    public void showNumberCastError() {
        String errorMessage = "☹ OOPS!!! Please input a valid index (i.e. a number)";
        System.out.println(Ui.formatText(errorMessage));
    }

    /**
     * Shows all tasks to the user in the search result.
     *
     * @param searchResults String representation of all string results.
     */
    public static void showSearchResults(String searchResults) {
        String message = "Here are the matching tasks in your list:\n" + searchResults;
        System.out.println(Ui.formatText(message));
    }


    /**
     * Styles a given text with indentation and wraps the text around horizontal lines.
     *
     * @param text String that needs to be styled.
     * @return Formatted String that has proper indentation and wrapped around horizontal lines.
     */
    private static String formatText(String text) {
        final String horizontalLine = "\t---------------------------------------------------------------------\n";

        String[] lines = text.split("\\r?\\n");
        StringBuilder formattedText = new StringBuilder(horizontalLine);
        for (String line : lines) {
            formattedText.append("\t").append(line).append("\n");
        }

        return formattedText + horizontalLine;
    }
}
