package scruffles;

import java.time.LocalDate;

/**
 * A task that has to be done within a certain period
 *
 * @author Shamus Tan
 */
public class DoWithinPeriod extends Task {

    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Constructor for DoWithinPeriod
     *
     * @param description the name of the DoWithinPeriod task
     * @param startDate the starting date of the period the task needs to be done within
     * @param endDate the ending date of the period the task needs to be done within
     */
    public DoWithinPeriod(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructor for DoWithinPeriod
     *
     * @param description the name of the DoWithinPeriod task
     * @param startDate the starting date of the period the task needs to be done within
     * @param endDate the ending date of the period the task needs to be done within
     * @param isDone whether the task has been done
     */
    public DoWithinPeriod(String description, LocalDate startDate, LocalDate endDate, boolean isDone) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String date = startDate.getDayOfMonth() + " " + startDate.getMonth().toString() + " " + startDate.getYear()
                + " and " + endDate.getDayOfMonth() + " " + endDate.getMonth().toString() + " " + endDate.getYear();
        return "[P]" + super.toString() + " (between: " + date + ")";
    }
}
