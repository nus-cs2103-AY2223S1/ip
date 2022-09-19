package poolsheen.command;

import java.util.ArrayList;

import poolsheen.PoolsheenException;
import poolsheen.Storage;
import poolsheen.TaskList;
import poolsheen.Ui;

/**
 * Represents a UpdateCommand which when executed will cause the Poolsheen program to
 * update the task of that respective position which Poolsheen remembers.
 * For example: update 1
 */
public class UpdateCommand extends Command {
    /**
     * Initialises an Update Command.
     * @param rest The rest of the string that has been parsed.
     */
    public UpdateCommand(ArrayList<String> rest) {
        super(false, rest);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tl, Ui ui, Storage storage) {
        if (rest.isEmpty() || rest.size() <= 1) {
            throw new PoolsheenException(String.join(" ", rest),
                    "update", "Please enter at least 1 appropriate integer and 1 string.");
        }

        int pos = java.lang.Integer.parseInt(rest.get(0));
        //Remove position from command
        rest.remove(0);
        boolean isOutOfBounds = ((pos - 1) > (tl.getSize() - 1)) || (pos <= 0);
        if (isOutOfBounds) {
            throw new PoolsheenException("Index out of bounds", "update", "Enter an appropriate integer");
        }

        String updateType = rest.get(0).toUpperCase();
        //Remove updateType specifier from command
        rest.remove(0);
        String updateContent = String.join(" ", rest);
        boolean isEmptyUpdateContent = updateContent.length() == 0;
        if (isEmptyUpdateContent) {
            throw new PoolsheenException("No update data provided", "update", "Please enter a non-empty string");
        }

        switch (updateType) {
        case "DESC":
            tl.updateDesc(pos, updateContent);
            break;
        case "TIME":
            tl.updateTime(pos, updateContent);
            break;
        default:
            throw new PoolsheenException(updateType, "update", "Please enter an appropriate update specifier");
        }
        return ui.say("Poolsheen has updated the task at position " + pos);
    }
}
