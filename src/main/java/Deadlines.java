import java.time.LocalDate;

public class Deadlines extends Task {

    protected LocalDate date;

    public Deadlines(String description, String by) {
        super(description);
        LocalDate date = LocalDate.parse(by);
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
        return "[D]" + super.toString() + " (by: "+ this.dateString() + ")";
    }

    @Override
    public boolean checkDate(LocalDate anoDate) {
        return anoDate.equals(this.date);
    }

    @Override
    public String saveString() {
        return "D " + "|" + (this.isDone? 1 : 0 ) +
                "| " + this.description + " | " + this.date + "\n";
    }
}
