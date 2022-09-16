package duke;

/**
 * Prints messages for user.
 */
public abstract class Ui {
    protected String welcomeMessage = "Hello! I'm Zuke.\nWhat can I do for you?";
    protected String loadingErrorMessage =
            "There was a problem loading the tasks from the output file. Starting with empty list.";

    public abstract void printDivider();

    public abstract void printWithDivider(String s);

    public abstract void println(String s);

    public abstract void showWelcome();

    public abstract void showLoadingError();
}
