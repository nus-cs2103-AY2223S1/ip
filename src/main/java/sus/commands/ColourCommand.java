package sus.commands;

import sus.common.Messages;
import sus.storage.StorageFile;
import sus.task.TaskList;
import sus.ui.TextUi;

/**
 * Sets the output colour of the CLI.
 */
public class ColourCommand extends Command {

    public static final String COMMAND_WORD = "colour";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Set text output colour.\n"
            + "\tColours supported: black, red, green, yellow, blue, magenta, cyan, white\n"
            + "\tEx: " + COMMAND_WORD + " <colour>";

    private final String colour;

    /**
     * Constructor.
     *
     * @param colour output colour of CLI
     */
    public ColourCommand(String colour) {
        this.colour = colour.toUpperCase();
    }

    @Override
    public CommandResult execute(TaskList taskList, TextUi textUi, StorageFile storage) {
        textUi.setOutputColor(colour);
        return new CommandResult(String.format(Messages.MESSAGE_OUTPUT_COLOUR_SET, colour));
    }
}
