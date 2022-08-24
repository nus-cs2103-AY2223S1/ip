import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        int year = Integer.parseInt(by.substring(0,4));
        int month = Integer.parseInt(by.substring(5,7));
        int day = Integer.parseInt(by.substring(8,10));
        int hours = Integer.parseInt(by.substring(11,13));
        int minutes = Integer.parseInt(by.substring(13, 15));

        LocalDateTime date = LocalDateTime.of(year, month, day, hours, minutes);
        this.by = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.getMonth() +" " + by.getDayOfMonth()  + " " + by.getYear() 
            + " " + String.format("%02d", by.getHour()) + " " + String.format("%02d", by.getMinute()) + ")";
    }
}
