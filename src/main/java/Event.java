import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private LocalDate date;
    private String time;
    private String straightLine = "  ----------------------------------------------------------------------------------";

    /**
     * A constructor to initialize the event.
     *
     * @param isDone A boolean to indicate if the event is done.
     * @param eventDescription A string to detail the event.
     * @param index The task number in the list of tasks to do.
     * @param date A LocalDate to detail date of event.
     * @param time A string to detail time of event.
     */
    public Event(boolean isDone, String eventDescription, int index, LocalDate date, String time) {
        super(isDone, eventDescription, index);
        this.date = date;
        this.time = time;
    }

    /**
     * Changes the task from undone to done and produces output in the console
     * to let user know it has been changed.
     */
    public void markDone() {
        super.markDone();
    }

    /**
     * Changes the task from done to undone and produces output in the console
     * to let user know it has been changed.
     */
    public void markUndone() {
        super.markUndone();
    }

    /**
     * Updates the index of the task to reflect the task's position in
     * the list of tasks.
     *
     * @param newIndex The current index of the event in the list of tasks.
     */
    @Override
    public void setIndex(int newIndex) {
        super.setIndex(newIndex);
    }

    /**
     * Outputs in the console to let user know full details of the event and inform
     * the user it has been added to list of tasks.
     */
    @Override
    public void printAdded() {
        System.out.println(straightLine + "\n  Looks like you have a new event:\n    [E][ ] " + this.getDescription()
                            + " (at: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + time + ")" +
                            "\n  " + this.getIndex() + " tasks left, 頑張れ!\n" + straightLine + "\n");
    }

    /**
     * Outputs in the console the details of the event.
     */
    @Override
    public void printTask() {
        if (!this.getStatus()) {
            System.out.println("  " + this.getIndex() + ".[E][ ] " + this.getDescription() + " (at: " +
                    date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + time + ")");
        } else {
            System.out.println("  " + this.getIndex() + ".[E][X] " + this.getDescription() + " (at: " +
                    date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + time + ")");
        }
    }

    /**
     * Outputs in the console the details of the event and informs users the task
     * has been deleted.
     */
    @Override
    public void printDeleted() {
        if (!this.getStatus()) {
            System.out.println(straightLine + "\n  Task deleted!\n    [E][ ] " + this.getDescription()
                    + "(at: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + time + ")");
        } else {
            System.out.println(straightLine + "\n  Task deleted!\n    [E][X] " + this.getDescription()
                    + "(at: " + date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + time + ")");
        }
    }

}
