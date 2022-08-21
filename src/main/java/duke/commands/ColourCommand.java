package duke.commands;

import duke.DukeException;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

public class ColourCommand extends Command {

    public static final String COMMAND_WORD = "colour";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Set text output colour.\n"
            + "Example: " + COMMAND_WORD;

    private final String colour;

    public ColourCommand(String inputColour) {
        colour = inputColour.toUpperCase();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, StorageFile storage) throws DukeException {
        ui.setOutputColor(colour);
    }
}
