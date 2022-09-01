import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private String dateTime;
    private LocalDateTime date;

    public Deadline(String name, boolean completed, String dateTime) {
        super(name, completed);
        this.dateTime = dateTime;
        applyDate(dateTime);
    }

    @Override
    public String getTime() {
        return dateTime;
    }

    @Override
    public String getTaskType() {
        return "D";

    }

    private void applyDate(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.date = LocalDateTime.parse(dateTime, formatter);
    }

    public String getDateTime(){
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    public String getDateTime(String format){
        return date.format(DateTimeFormatter.ofPattern(format));
    }

    public String getDate(){
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public Task toggleCompleted(){
        return new Deadline(getName(),!isCompleted(), dateTime);

    }

    public static void validDateTime(String date) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime d = LocalDateTime.parse(date, formatter);
    }

    @Override
    public String toString(){
        return String.format("[D][%s] %s (by: %s)",checkMarked(),getName(),getDateTime());
    }

}
