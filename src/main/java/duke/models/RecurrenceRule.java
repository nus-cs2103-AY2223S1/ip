package duke.models;

import java.time.LocalDate;

public class RecurrenceRule {
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected int interval = 7;

    public RecurrenceRule(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public RecurrenceRule(LocalDate startDate, LocalDate endDate, int interval) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.interval = interval;
    }

    public void updateStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String formatForSave() {
        return startDate + " | " + endDate + " | " + interval;
    }
}
