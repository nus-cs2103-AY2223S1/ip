public class Deadline extends Task{

    private String date;
    private String straightLine = "  ----------------------------------------------------------------------------------";

    /**
     * A constructor to initialize a Deadline object.
     *
     * @param isDone A boolean to indicate if the deadline is completed.
     * @param deadlineDescription A string to describe the deadline.
     * @param index An integer to indicate the position of the Deadline in list of tasks.
     * @param date A String to indicate the date to be completed by.
     */
    public Deadline(boolean isDone, String deadlineDescription, int index, String date) {
        super(isDone, deadlineDescription, index);
        this.date = date;
    }

    /**
     * A method to update the deadline from incomplete to complete.
     */
    public void markDone() {
        super.markDone();
    }

    /**
     * A method to update the deadline from complete to incomplete.
     */
    public void markUndone() {
        super.markUndone();
    }

    /**
     * A method to update the index of the deadline in the list of tasks.
     *
     * @param newIndex The most current integer index of the task.
     */
    @Override
    public void setIndex(int newIndex) {
        super.setIndex(newIndex);
    }

    /**
     * Outputs the deadline added to the list of tasks in the console.
     */
    @Override
    public void printAdded() {
        System.out.println(straightLine + "\n  Yep, it's in!\n    [D][ ] " + this.getDescription() + " (by:" + date
                            + ")\n  " + this.getIndex() + " tasks left, 頑張れ!\n" + straightLine + "\n");
    }

    /**
     * Outputs the full details of the deadline in the console.
     */
    @Override
    public void printTask() {
        if (!this.getStatus()) {
            System.out.println("  " + this.getIndex() + ".[D][ ] " + this.getDescription() + " (by:" +
                                date + ")");
        } else {
            System.out.println("  " + this.getIndex() + ".[D][X] " + this.getDescription() + " (by:" +
                    date + ")");
        }
    }

    /**
     * Outputs the full details of the deadline being deleted in the console.
     */
    public void printDeleted() {
        if (!this.getStatus()) {
            System.out.println(straightLine + "\n  Task deleted!\n    [D][ ] " + this.getDescription()
                    + "(by:" + date + ")");
        } else {
            System.out.println(straightLine + "\n  Task deleted!\n    [D][X] " + this.getDescription()
                    + "(by:" + date + ")");
        }
    }
}
