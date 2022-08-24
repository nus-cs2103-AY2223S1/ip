import java.time.LocalDateTime;

public class Event extends Task{
    protected LocalDateTime at;

    public Event(String description, String at) {
        super(description);

        int year = Integer.parseInt(at.substring(0,4));
        int month = Integer.parseInt(at.substring(5,7));
        int day = Integer.parseInt(at.substring(8,10));
        int hours = Integer.parseInt(at.substring(11,13));
        int minutes = Integer.parseInt(at.substring(13, 15));

        LocalDateTime date = LocalDateTime.of(year, month, day, hours, minutes);
        this.at = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at.getMonth() +" " + at.getDayOfMonth()  + " " + at.getYear() 
            + " " + String.format("%02d", at.getHour()) + " " + String.format("%02d", at.getMinute()) + ")";
    }

}
