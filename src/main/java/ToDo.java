public class ToDo extends Task {

    private String straightLine = "  ----------------------------------------------------------------------------------";

    public ToDo(boolean isDone, String toDoDescription, int index) {
        super(isDone, toDoDescription, index);
    }

    public void markDone() {
        super.markDone();
    }

    public void markUndone() {
        super.markUndone();
    }

    @Override
    public void printAdded() {
        System.out.println(straightLine + "\n  Its been added!\n    [T][ ] " + this.getDescription()
                            + "\n  " + this.getIndex() + " tasks left! ^-^\n" + straightLine + "\n");
    }

    @Override
    public void printTask() {
        if (!this.getStatus()) {
            System.out.println("  " + this.getIndex() + ".[T][ ] " + this.getDescription());
        } else {
            System.out.println("  " + this.getIndex() + ".[T][X] " + this.getDescription());
        }
    }


}
