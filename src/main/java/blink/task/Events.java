package blink.task;

import java.time.LocalDate;

/**
 * Events task that contains a description, boolean to indicate if
 * it has been marked and date.
 */
public class Events extends Task{

    protected LocalDate date;

    /**
     * Constructor of the Events object that takes in description
     * and date of event.
     *
     * @param description Description of Events object
     * @param at Date of Events object
     */
    public Events(String description, String at) {
        super(description);
        LocalDate date = LocalDate.parse(at);
        this.date = date;
    }

    /**
     * String representation of Events Object.
     *
     * @return [E] to represent deadline and information indicating
     * if its marked, description and associated date
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "+ this.dateString() + ")";
    }

    /**
     * Checks if date passed into method is the same
     * as current Events object.
     *
     * @param anoDate Date to check equality with
     * @return True if the dates are equal and false if not
     */
    @Override
    public boolean checkDate(LocalDate anoDate) {
        return anoDate.equals(this.date);
    }

    /**
     * String representation of Events object in storage file.
     *
     * @return String to represent the information of current Events object
     */
    @Override
    public String saveString() {
        return "E " + "|" + (this.isDone? 1 : 0 )
                + "| " + this.description + " | " + this.date + "\n";
    }

    private String dateString() {
        String month = this.date.getMonth().toString();
        int year = this.date.getYear();
        int day = this.date.getDayOfMonth();
        return month + " " + day + " " + year + " " + this.date.getDayOfWeek().toString();
    }
}
