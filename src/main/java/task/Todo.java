package task;

import exception.DukeException;

public class Todo extends Task {
    public Todo(String taskItem) {
        super(taskItem);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public static void validateInput(String[] input) throws DukeException {
        if (input.length < 2 || input[1].strip().equals("")) {
            throw new DukeException(DukeException.ErrorCode.MISSING_TODO_ITEM);
        }
    }

    @Override
    public String encode() {
        int markedStatus = getIsMarked() ? 1 : 0;
        return String.format("T,%d,%s\n", markedStatus, getTaskItem());
    }
}
