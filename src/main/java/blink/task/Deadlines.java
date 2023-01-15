package blink.task;

import java.time.LocalDate;

/**
 * Deadlines task that contains a description, boolean to indicate if
 * it has been marked and date.
 */
public class Deadlines extends Task {

    protected LocalDate date;

    /**
     * Constructor of the Deadlines object that takes in description
     * and date of deadline.
     *
     * @param description Description of Deadlines object
     * @param by Date of Deadlines object
     */
    public Deadlines(String description, String by) {
        super(description);
        LocalDate date = LocalDate.parse(by);
        this.date = date;
    }

    /**
     * String representation of Deadlines Object.
     *
     * @return [D] to represent deadline and information indicating
     *     if its marked, description and to be done by which date
     */
    @Override
    public String toString() {
        String date = " (by: " + this.dateString() + ")";
        String tags = this.tagToString();
        return "[D]" + super.toString() + date + tags;
    }

    /**
     * Checks if date passed into method is the same
     * as current Deadlines object.
     *
     * @param anoDate Date to check equality with
     * @return True if the dates are equal and false if not
     */
    @Override
    public boolean checkDate(LocalDate anoDate) {
        return anoDate.equals(this.date);
    }

    /**
     * String representation of Deadline object in storage file.
     *
     * @return String to represent the information of current Deadline object
     */
    @Override
    public String saveString() {
        String taskMark = "|" + (this.isDone ? 1 : 0) + "| ";
        String info = this.description + " | " + this.date;
        return "D " + taskMark + info + this.saveTagString() + "\n";
    }

    private String dateString() {
        String month = this.date.getMonth().toString();
        int year = this.date.getYear();
        int day = this.date.getDayOfMonth();
        return month + " " + day + " " + year + " " + this.date.getDayOfWeek().toString();
    }
}
