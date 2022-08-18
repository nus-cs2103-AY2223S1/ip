public class ToDo extends Task {

    private String straightLine = "  ----------------------------------------------------------------------------------";

    /**
     * A constructor to initialize the ToDo object.
     *
     * @param isDone A boolean to indicate if the ToDo is done.
     * @param toDoDescription A String to detail what to do.
     * @param index An integer to indicate the position of the ToDo in the list of tasks.
     */
    public ToDo(boolean isDone, String toDoDescription, int index) {
        super(isDone, toDoDescription, index);
    }

    /**
     * A method to update the ToDo from incomplete to complete.
     */
    public void markDone() {
        super.markDone();
    }

    /**
     * A method to update the ToDo from complete to incomplete.
     */
    public void markUndone() {
        super.markUndone();
    }

    /**
     * A method to update the index of the ToDo in the list of tasks.
     *
     * @param newIndex The most current integer index of the ToDo.
     */
    @Override
    public void setIndex(int newIndex) {
        super.setIndex(newIndex);
    }

    /**
     * Outputs the full details of the ToDo added to the console.
     */
    @Override
    public void printAdded() {
        System.out.println(straightLine + "\n  Its been added!\n    [T][ ] " + this.getDescription()
                            + "\n  " + this.getIndex() + " tasks left! ^-^\n" + straightLine + "\n");
    }

    /**
     * Outputs the full details of the ToDo to the console.
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
     * Outputs the full details of the ToDo that is deleted to the console.
     */
    @Override
    public void printDeleted() {
        if (!this.getStatus()) {
            System.out.println(straightLine + "\n  Task deleted!\n    [T][ ] " + this.getDescription());
        } else {
            System.out.println(straightLine + "\n  Task deleted!\n    [T][X] " + this.getDescription());
        }
    }

}
