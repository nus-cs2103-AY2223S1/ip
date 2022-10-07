package ui;

/**
 * Handles interactions with the user.
 */
public class Ui {
    /**
     * Prints the formatted Bocil's response.
     *
     * @param output Formatted response of Bocil.
     */
    public void printOutput(String output) {
        System.out.println(output);
    }

    /**
     * Shows the error message of the exception.
     *
     * @param e Exception being handled.
     * @return Message of the error.
     */
    public String showError(Exception e) {
        return e.getMessage();
    }
}
