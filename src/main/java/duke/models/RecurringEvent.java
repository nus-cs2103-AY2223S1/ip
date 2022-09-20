package duke.models;

import java.time.LocalDate;

public class RecurringEvent extends Event {
    private RecurrenceRule recurrenceRule;

    public RecurringEvent(String description, LocalDate at, LocalDate end) {
        super(description, at);
        this.recurrenceRule = new RecurrenceRule(at, end);
    }

    public RecurringEvent(String description, RecurrenceRule recurrenceRule) {
        super(description, recurrenceRule.startDate);
        this.recurrenceRule = recurrenceRule;
    }

    public RecurringEvent(String description, LocalDate at,
                          LocalDate start, LocalDate end, int interval, boolean isDone) {
        super(description, at, isDone);
        this.recurrenceRule = new RecurrenceRule(start, end, interval);
    }

    public void updateRecurrenceRule() {
        this.recurrenceRule.updateStartDate(at.plusDays(recurrenceRule.interval));
    }

    public RecurringEvent createNextRecurringEvent() {
        updateRecurrenceRule();
        if (recurrenceRule.endDate.isBefore(recurrenceRule.startDate)) {
            return null;
        }
        return new RecurringEvent(this.content, recurrenceRule);
    }
    @Override
    public void markAsDone(TaskList taskList) {
        this.isDone = true;
        if (this.at.isBefore(recurrenceRule.startDate)) {
           return;
        }
        // Automatically create the next event if it has not been created
        RecurringEvent nextEvent = createNextRecurringEvent();
        if (nextEvent != null) {
            taskList.addTask(createNextRecurringEvent());
        }
    }

    public String formatForSave() {
        return "R" + super.formatForSave() + " | " +  recurrenceRule.formatForSave();
    }

    /**
     * Formats the string representation of the recurring event object for display.
     *
     * @return The string representation of a recurring event object for display.
     */
    @Override
    public String toString() {
        return "[R]" + super.toString();
    }
}
