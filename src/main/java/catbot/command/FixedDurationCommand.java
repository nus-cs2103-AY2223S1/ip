package catbot.command;

import catbot.TaskList;
import catbot.Ui;
import catbot.exception.EmptyDurationException;
import catbot.exception.EmptyTaskException;
import catbot.exception.InvalidArgumentException;
import catbot.task.FixedDuration;

import java.util.regex.Pattern;

/**
 * A class for the fixed duration command.
 */
public class FixedDurationCommand extends Command {

    /** The description of the fixed duration task as input by the user. */
    private final String description;

    /**
     * Constructor for the FixedDurationCommand class.
     *
     * @param description The description of the fixed duration task as input by the user.
     */
    public FixedDurationCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the fixed duration command.
     *
     * @param taskList The taskList storing all tasks.
     * @param ui       The ui responsible for interactions with the user.
     * @throws EmptyTaskException       If the followup text after the command is empty.
     * @throws InvalidArgumentException If the followup text after the command and description is not "/takes" or if
     *                                  the format for duration is wrong.
     * @throws EmptyDurationException   If the followup text after "/takes" is empty.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws EmptyTaskException, InvalidArgumentException,
            EmptyDurationException {
        String[] split = this.description.split("/takes ");

        if (isEmptyTask(split)) {
            throw new EmptyTaskException("fixed");
        }
        if (split[0].equals(this.description)) {
            throw new InvalidArgumentException("fixed", "/takes");
        }
        if (split.length == 1) {
            throw new EmptyDurationException("fixed", "/takes");
        }

        int[] duration = {0, 0};
        splitDuration(duration, split[1]);
        taskList.add(new FixedDuration(split[0].trim(), duration[0], duration[1]));
        this.response += "Added the task with fixed duration: \n" + taskList.getLast() + "\n" + ui.printListCount();

    }

    /**
     * Splits the input string into hours and minutes in the int array.
     *
     * @param duration The array to store the hours and minutes in int.
     * @param input    The string containing the duration to be split up.
     * @throws InvalidArgumentException If the input text does not follow the correct format for duration.
     */
    private void splitDuration(int[] duration, String input) throws InvalidArgumentException {
        if (!isValidInput(input)) {
            throw new InvalidArgumentException("fixed", "h/min");
        }

        String[] splitInput = input.split(" ");
        if (splitInput.length == 1) {
            if (input.contains("h")) {
                duration[0] = getIntFromDuration(splitInput[0]);
            } else if (input.contains("min")) {
                duration[1] = getIntFromDuration(splitInput[0]);
            } else {
                throw new InvalidArgumentException("fixed", "h/min");
            }
            return;
        }

        duration[0] = getIntFromDuration(splitInput[0]);
        duration[1] = getIntFromDuration(splitInput[1]);
    }

    /**
     * Checks if the input follows the correct format for duration.
     *
     * @param input The string input to be checked.
     * @return True if the string follows the format, false otherwise.
     */
    private boolean isValidInput(String input) {
        Pattern patternHour = Pattern.compile("[0-9]+h", Pattern.CASE_INSENSITIVE);
        Pattern patternMins = Pattern.compile("[0-9]+mins?", Pattern.CASE_INSENSITIVE);
        Pattern patternHourMins = Pattern.compile("[0-9]+h\\s[0-9]+mins?", Pattern.CASE_INSENSITIVE);
        return patternHour.matcher(input).matches() || patternMins.matcher(input).matches()
                || patternHourMins.matcher(input).matches();
    }

    /**
     * Gets the integer value from the input string containing letters.
     *
     * @param s The input string to retrieve the int value from.
     * @return The int value from the string.
     */
    private int getIntFromDuration(String s) {
        return Integer.parseInt(s.replaceAll("\\D", ""));
    }

    /**
     * Checks if the input text is an empty command to add a task.
     *
     * @param split The string array of words split up.
     * @return True if the task is empty, false otherwise.
     */
    private boolean isEmptyTask(String[] split) {
        return this.description.trim().equals("") || split.length == 0 || split[0].equals("");
    }
}
