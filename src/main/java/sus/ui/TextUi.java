package sus.ui;

import java.io.PrintStream;
import java.util.Scanner;

import sus.commands.CommandResult;
import sus.common.Messages;

/**
 * Represents the ui used to interact with the user.
 */
public class TextUi {

    private static final String DIVIDER = "────────────────────────────────────────────────────────────";
    private static final String PREFIX = "duke >> ";

    private static final PrintStream out = System.out;
    private final Scanner in;
    private Colour currentColour = Colour.GREEN;

    /**
     * Represents the available colours the CLI can show.
     */
    public enum Colour {
        RESET("\u001B[0m"),
        BLACK("\u001B[30m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"),
        BLUE("\u001B[34m"),
        PURPLE("\u001B[35m"),
        CYAN("\u001B[36m"),
        WHITE("\u001B[36m");

        private final String colourCode;

        Colour(String colourCode) {
            this.colourCode = colourCode;
        }

        private String getColourCode() {
            return colourCode;
        }

        /**
         * Check if input colour is supported
         * @param colour specified colour
         * @return true if colour is supported, false otherwise
         */
        public static boolean contains(String colour) {
            for (Colour c : Colour.values()) {
                if (c.name().equalsIgnoreCase(colour)) {
                    return true;
                }
            }

            return false;
        }
    }

    /**
     * Sets up the Ui, loads the Jansi library (enables colour support on Windows/Unix)
     */
    public TextUi() {
        in = new Scanner(System.in);
    }

    /**
     * Gets the command inputted by the user and returns it.
     */
    public String getUserCommand() {
        out.print("Enter command: ");
        String inputLine = in.nextLine();
        out.println(Colour.PURPLE.getColourCode() + PREFIX + inputLine + Colour.RESET.getColourCode());

        return inputLine;
    }

    /**
     * Shows the result of the command to the user in the GUI.
     */
    public void showResultToUser(CommandResult result) {
        showDivider();
        showWithCurrentColour(result.getCommandResult());
        showDivider();
    }

    /**
     * Shows messages to the user with the set output colour.
     */
    public void showWithCurrentColour(String... messages) {
        for (String s : messages) {
            out.println(currentColour.getColourCode() + s + Colour.RESET.getColourCode());
        }
    }

    public void setOutputColor(String inputColour) {
        currentColour = Colour.valueOf(inputColour);
    }

    /**
     * Shows the welcome message when Duke starts.
     */
    public void showWelcome() {
        showDivider();
        showWithCurrentColour(Messages.MESSAGE_WELCOME);
        showDivider();
    }

    /**
     * Shows the exit message when user inputs the exit command.
     */
    public void showExit() {
        showDivider();
        showWithCurrentColour(Messages.MESSAGE_EXIT);
        showDivider();
    }

    private void showDivider() {
        showWithCurrentColour(DIVIDER);
    }

}
