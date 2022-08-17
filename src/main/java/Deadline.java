public class Deadline extends Task{

    private String date;
    private String straightLine = "  ----------------------------------------------------------------------------------";

    public Deadline(boolean isDone, String deadlineDescription, int index, String date) {
        super(isDone, deadlineDescription, index);
        this.date = date;
    }

    public void markDone() {
        super.markDone();
    }

    public void markUndone() {
        super.markUndone();
    }

    @Override
    public void setIndex(int newIndex) {
        super.setIndex(newIndex);
    }

    @Override
    public void printAdded() {
        System.out.println(straightLine + "\n  Yep, it's in!\n    [D][ ] " + this.getDescription() + " (by:" + date
                            + ")\n  " + this.getIndex() + " tasks left, 頑張れ!\n" + straightLine + "\n");
    }

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
