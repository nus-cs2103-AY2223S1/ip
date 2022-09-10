package duke;

public class ToDo extends Task {

    /**
     * A constructor to initialize the duke.ToDo object.
     *
     * @param isDone A boolean to indicate if the duke.ToDo is done.
     * @param toDoDescription A String to detail what to do.
     * @param index An integer to indicate the position of the duke.ToDo in the list of tasks.
     */
    public ToDo(boolean isDone, String toDoDescription, int index) {
        super(isDone, toDoDescription, index);
    }

    /**
     * Changes the task from undone to done.
     *
     * @return a String representation on details of the ToDo done.
     */
    public String markDone() {
        return super.markDone();
    }

    /**
     * Changes the task from done to undone.
     *
     * @return a String representation on details of the ToDo undone.
     */
    public String markUndone() {
        return super.markUndone();
    }

    /**
     * Updates the index of the duke.ToDo in the list of tasks.
     *
     * @param newIndex The most current integer index of the duke.ToDo.
     */
    @Override
    public void setIndex(int newIndex) {
        super.setIndex(newIndex);
    }

    /**
     * Returns a string describing the ToDo added to the list of tasks.
     *
     * @return a String representation of ToDo added.
     */
    @Override
    public String printAdded() {
        return "\n  Its been added!\n    [T][ ] " + this.getDescription()
                + "\n  " + this.getIndex() + " tasks left! ^-^\n";
    }

    /**
     * Returns a string on the full details of the ToDo.
     *
     * @return a String representation of ToDo details.
     */
    @Override
    public String printTask() {
        if (!this.getStatus()) {
            return "  " + this.getIndex() + ".[T][ ] " + this.getDescription();
        } else {
            return "  " + this.getIndex() + ".[T][X] " + this.getDescription();
        }
    }

    /**
     * Returns a string representation of the ToDo.
     *
     * @return a String describing the ToDo.
     */
    @Override
    public String toString() {
        if (!this.getStatus()) {
            return this.getIndex() + ".[T][ ] " + this.getDescription();
        } else {
            return this.getIndex() + ".[T][X] " + this.getDescription();
        }
    }

    @Override
    public String savedString() {
        if (!this.getStatus()) {
            return "TN<" + this.getDescription() + ">";
        } else {
            return "TY<" + this.getDescription() + ">";
        }
    }

    /**
     * Returns a string describing the ToDo deleted from the list of tasks.
     *
     * @return a String representation of the ToDo deleted.
     */
    @Override
    public String printDeleted() {
        if (!this.getStatus()) {
            return "\n  Task deleted!\n    [T][ ] " + this.getDescription();
        } else {
            return "\n  Task deleted!\n    [T][X] " + this.getDescription();
        }
    }

}
