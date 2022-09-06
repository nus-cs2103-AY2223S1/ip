package duke.command;

import duke.DukeException;
import duke.Response;
import duke.util.TaskList;

/**
 * Handles the command 'tag'.
 */
public class TagCommand extends Command {
    private static final String TAG_ERROR_MESSAGE = "Please follow the format \n'tag NUMBER #TAG'";
    private String input;
    private String[] segments;

    /**
     * Constructor for a new Tag command.
     *
     * @param input the input
     */
    public TagCommand(String input) {
        this.input = input;
        this.segments = input.split(" ", 2);
    }

    /**
     * Checks whether there is a tag '#'.
     *
     * @return if tag starts with '#'.
     */
    public boolean isFormattedTag() {
        return segments[1].startsWith("#");
    }

    /**
     * Checks whether tag is empty.
     *
     * @return whether tag is empty.
     */
    public boolean isNotEmptyTag() {
        return segments[1].length() > 1;
    }

    /**
     * Checks whether the input has 2 separate components
     *
     * @return if input has 2 separate components
     */
    public boolean isTagged() {
        return segments.length == 2;
    }

    /**
     * Checks whether the tag is valid.
     *
     * @return if tag is valid.
     */
    public boolean isValidTag() {
        return isTagged() && isNotEmptyTag() && isFormattedTag();
    }

    /**
     * Runs the command 'tag'.
     * @inheritDoc
     * @param taskList List of current tasks.
     * @param builder
     * @throws DukeException Duke Exception for marking out of bounds.
     */
    @Override
    public void run(TaskList taskList, Response builder) throws DukeException {
        if (isValidTag()) {
            try {
                int index = Integer.parseInt(segments[0]) - 1;
                taskList.getTask(index).setTag(segments[1]);
                builder.append("I've tagged this task! \n  "
                        + taskList.getTask(index).toString());
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Index is out of bounds!");
            } catch (NumberFormatException e) {
                throw new DukeException(TAG_ERROR_MESSAGE);
            }
        } else {
            throw new DukeException(TAG_ERROR_MESSAGE);
        }
    }
}
