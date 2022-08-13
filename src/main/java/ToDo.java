package main.java;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    /**
     * Override 'toString' method to return status and description of 'ToDo'
     * object.
     * @return [T][COMPLETION STATUS][TASK DESCRIPTION]
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
