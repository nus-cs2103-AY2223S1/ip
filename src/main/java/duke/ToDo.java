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
     * Updates the duke.ToDo from incomplete to complete.
     */
    public String markDone() {
        return super.markDone();
    }

    /**
     * Updates the duke.ToDo from complete to incomplete.
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
     * Outputs the full details of the duke.ToDo added to the console.
     */
    @Override
    public String printAdded() {
        return "\n  Its been added!\n    [T][ ] " + this.getDescription()
                + "\n  " + this.getIndex() + " tasks left! ^-^\n";
    }

    /**
     * Outputs the full details of the duke.ToDo to the console.
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
     * Returns a string representation of the duke.ToDo.
     *
     * @return string describing the duke.ToDo.
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
    public String toSavedString() {
        if (!this.getStatus()) {
            return "TN<" + this.getDescription() + ">";
        } else {
            return "TY<" + this.getDescription() + ">";
        }
    }
    /**
     * Outputs the full details of the duke.ToDo that is deleted to the console.
     */
    @Override
    public String printDeleted() {
        if (!this.getStatus()) {
            return "\n  duke.Task deleted!\n    [T][ ] " + this.getDescription();
        } else {
            return "\n  duke.Task deleted!\n    [T][X] " + this.getDescription();
        }
    }

}
