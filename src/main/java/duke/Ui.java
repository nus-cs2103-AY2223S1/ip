package duke;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * This class deals with interactions with the user.
 */
public class Ui {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("HH:mm, E, MMM dd yyyy");
    private static final Scanner SCANNER = new Scanner(System.in);
    private StorageList storageList;
    private String lastInput;
    private String lastCommand;

    /**
     * Constructor for the Ui class.
     *
     * @param storageList StorageList to be used
     */
    public Ui(StorageList storageList) {
        this.storageList = storageList;
    }

    /**
     * Handles the user input and calls the appropriate method.
     *
     * @return true if the user wants to continue, false otherwise
     */
    public String readCommand() {
        String input = SCANNER.nextLine();
        lastInput = input;
        String command = input.split(" ")[0];
        lastCommand = command;
        return command;
    }

    /**
     * Handles the user input passed in and calls the appropriate method.
     *
     * @return true if the user wants to continue, false otherwise
     */
    public String readCommand(String input) {
        lastInput = input;
        String command = input.split(" ")[0];
        lastCommand = command;
        return command;
    }


    /**
     * Return the formatter for the DateTime input.
     *
     * @return DateTimeFormatter
     */
    public static DateTimeFormatter getInputFormatter() {
        return INPUT_FORMATTER;
    }

    /**
     * Return the formatter for the DateTime output.
     *
     * @return DateTimeFormatter
     */
    public static DateTimeFormatter getOutputFormatter() {
        return OUTPUT_FORMATTER;
    }

    /**
     * Returns the last input.
     *
     * @return last input
     */
    public String getLastInput() {
        return lastInput;
    }

    /**
     * Returns the last command.
     *
     * @return last command
     */
    public String getLastCommand() {
        return lastCommand;
    }

    /**
     * Sets the last input.
     *
     * @param lastInput last input
     */
    public void setLastInput(String lastInput) {
        this.lastInput = lastInput;
    }

    /**
     * Sets the last command.
     *
     * @param lastCommand last command
     */
    public void setLastCommand(String lastCommand) {
        this.lastCommand = lastCommand;
    }
}
