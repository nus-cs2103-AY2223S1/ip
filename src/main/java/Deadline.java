import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected LocalDateTime dateAndTime;

    public Deadline(String name, String dateAndTime) {
        super(name);

        String[] dateAndTimeSplit = dateAndTime.split(" ");
        String[] dateSplit = dateAndTimeSplit[0].split("[,./-]");

        this.dateAndTime = LocalDateTime.of(
                Integer.parseInt(dateSplit[0]),
                Integer.parseInt(dateSplit[1]),
                Integer.parseInt(dateSplit[2]),
                Integer.parseInt(dateAndTimeSplit[1]) / 100,
                Integer.parseInt(dateAndTimeSplit[1]) % 100
        );
    }

    public Deadline(String name, String dateAndTime, boolean isDone) {
        super(name, isDone);
        String[] dateAndTimeSplit = dateAndTime.split(" ");
        String[] dateSplit = dateAndTimeSplit[0].split("[,./-]");

        this.dateAndTime = LocalDateTime.of(
                Integer.parseInt(dateSplit[0]),
                Integer.parseInt(dateSplit[1]),
                Integer.parseInt(dateSplit[2]),
                Integer.parseInt(dateAndTimeSplit[1]) / 100,
                Integer.parseInt(dateAndTimeSplit[1]) % 100
        );
    }


    public String getDateString() {
        return this.dateAndTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public String tag() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", tag(), super.toString(), getDateString());
    }

    @Override
    public String savedString() {
        return String.format("%s,%s,%s", tag(), super.savedString(), getDateString());
    }
}
