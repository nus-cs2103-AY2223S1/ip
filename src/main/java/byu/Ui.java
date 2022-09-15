package byu;

import exceptions.DukeException;

/**
 * A user interface that deals with the interaction with users.
 */
public class Ui {

    static final String LOGO = "*\\(^o^)/*\n";
    static final String WELCOME_MESSAGE = "Bonjour~~ I'm Byu, your personal task tracker!\n"
            + "Nice to see you here!\n"
            + "Tracking your tasks brings you one step closer to finishing them on time.\n"
            + "Let's get started~\n";
    private static final String ERROR_FORMAT = "Ohno! Error: %s\n";

    private String output;

    /**
     * Returns the error message for an invalid input
     *
     * @param exception the exception indicating the error.
     * @return the error message.
     */
    public String getErrorOutput(DukeException exception) {
        return String.format(Ui.ERROR_FORMAT, exception.getMessage());
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getValidOutput() {
        return this.output;
    }

}
