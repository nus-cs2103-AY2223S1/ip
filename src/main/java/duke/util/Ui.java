package duke.util;

import iohelper.IoHelper;

/**
 * Represents the User Interface of duke.
 * Provides a welcome and goodbye text to be display in the command line.
 */
public class Ui {
    private final String DUKE_INTRODUCTION = "Hello, I'm your personal assistant, Duke.";
    private final String DUKE_HELP = "How can I assist you today?";
    private final String DUKE_END = "Pleasure to be at your service! Run me again if you need more assistance! :)";
    private final String divider = "__________________________________________________________";
    private IoHelper ioHelper;

    /**
     * Constructs Ui object which consist of IoHelper.
     */
    public Ui() {
        ioHelper = new IoHelper();
    }

    /**
     * Reads the command entered by the user.
     *
     * @return the string representation of the user's input
     */
    public String readCommand() {
        String text = ioHelper.getText();
        return text.strip();
    }

    /**
     * Displays duke's welcome message.
     */
    public void showWelcome() {
        ioHelper.print(DUKE_INTRODUCTION);
        ioHelper.print(DUKE_HELP);
    }

    /**
     * Displays duke's goodbye message.
     */
    public void showGoodbye() {
        ioHelper.print(DUKE_END);
        ioHelper.closeScanner();
    }

    /**
     * Displays a separator line.
     */
    public void showLine() {
        ioHelper.print(divider);
    }

    /**
     * Displays the String representation of message.
     *
     * @param message Object to be displayed.
     */
    public void show(Object message) {
        ioHelper.print(message);
    }

    public void show(Exception exception) {
        ioHelper.print(exception.getMessage());
    }

}
