package task;

import exception.DukeException;

/**
 * Represents a task to be done
 *
 * @author Bryan Lim Jing Xiang
 */
public class Todo extends Task {
    /**
     * {@inheritDoc}
     */
    public Todo(String taskItem) {
        super(taskItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Validates the input line that represents a command
     * to create a Todo object.
     *
     * @param input Input command to create a Todo
     * @throws DukeException If input is invalid
     */
    public static void validateInput(String[] input) throws DukeException {
        if (input.length < 2 || input[1].strip().equals("")) {
            throw new DukeException(DukeException.ErrorCode.MISSING_TODO_ITEM);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode() {
        int markedStatus = getIsMarked() ? 1 : 0;
        return String.format("T,%d,%s\n", markedStatus, getTaskItem());
    }
}
