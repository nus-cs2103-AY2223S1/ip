package blink.task;

import java.time.LocalDate;

public class Events extends Task{

    protected LocalDate date;

    public Events(String description, String at) {
        super(description);
        LocalDate date = LocalDate.parse(at);
        this.date = date;
    }

    private String dateString() {
        String month = this.date.getMonth().toString();
        int year = this.date.getYear();
        int day = this.date.getDayOfMonth();
        return month + " " + day + " " + year + " " + this.date.getDayOfWeek().toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateString() + ")";
    }

    @Override
    public boolean checkDate(LocalDate anoDate) {
        return anoDate.equals(this.date);
    }

    @Override
    public String saveString() {
        return "E " + "|" + (this.isDone? 1 : 0 ) + "| "
                + this.description + " | " + this.date + "\n";
    }
}
