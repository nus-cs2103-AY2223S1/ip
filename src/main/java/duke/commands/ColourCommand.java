package duke.commands;

import duke.common.Messages;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Sets the output colour of the program.
 */
public class ColourCommand extends Command {

    public static final String COMMAND_WORD = "colour";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Set text output colour.\n"
            + "\tColours supported: black, red, green, yellow, blue, magenta, cyan, white\n"
            + "\tEx.: " + COMMAND_WORD + " <colour>";

    private final String colour;

    public ColourCommand(String inputColour) {
        colour = inputColour.toUpperCase();
    }

    @Override
    public CommandResult execute(TaskList taskList, TextUi textUi, StorageFile storage) {
        textUi.setOutputColor(colour);
        return new CommandResult(String.format(Messages.MESSAGE_OUTPUT_COLOUR_SET, colour));
    }
}
