package duke;

public class ToDo extends Task {

    private String straightLine = "  ----------------------------------------------------------------------------------";

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
     * A method to update the duke.ToDo from incomplete to complete.
     */
    public void markDone() {
        super.markDone();
    }

    /**
     * A method to update the duke.ToDo from complete to incomplete.
     */
    public void markUndone() {
        super.markUndone();
    }

    /**
     * A method to update the index of the duke.ToDo in the list of tasks.
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
    public void printAdded() {
        System.out.println(straightLine + "\n  Its been added!\n    [T][ ] " + this.getDescription()
                + "\n  " + this.getIndex() + " tasks left! ^-^\n" + straightLine + "\n");
    }

    /**
     * Outputs the full details of the duke.ToDo to the console.
     */
    @Override
    public void printTask() {
        if (!this.getStatus()) {
            System.out.println("  " + this.getIndex() + ".[T][ ] " + this.getDescription());
        } else {
            System.out.println("  " + this.getIndex() + ".[T][X] " + this.getDescription());
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
    public String savedString() {
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
    public void printDeleted() {
        if (!this.getStatus()) {
            System.out.println(straightLine + "\n  duke.Task deleted!\n    [T][ ] " + this.getDescription());
        } else {
            System.out.println(straightLine + "\n  duke.Task deleted!\n    [T][X] " + this.getDescription());
        }
    }

}
