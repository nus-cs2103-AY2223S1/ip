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

    public DoWithinPeriod(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

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
