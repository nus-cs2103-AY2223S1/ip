public class Event extends Task{

    private String dateAndTime;
    private String straightLine = "  ----------------------------------------------------------------------------------";

    public Event(boolean isDone, String eventDescription, int index, String dateAndTime) {
        super(isDone, eventDescription, index);
        this.dateAndTime = dateAndTime;
    }

    public void markDone() {
        super.markDone();
    }

    public void markUndone() {
        super.markUndone();
    }


    @Override
    public void printAdded() {
        System.out.println(straightLine + "\n  Looks like you have a new event:\n    [D][ ] " + this.getDescription()
                            + " (at:" + dateAndTime + ")" + "\n  " + this.getIndex() + " tasks left, 頑張れ!\n" +
                            straightLine + "\n");
    }

    @Override
    public void printTask() {
        if (!this.getStatus()) {
            System.out.println("  " + this.getIndex() + ".[E][ ] " + this.getDescription() + " (at:" +
                    dateAndTime + ")");
        } else {
            System.out.println("  " + this.getIndex() + ".[E][X] " + this.getDescription() + " (at:" +
                    dateAndTime + ")");
        }
    }


}
