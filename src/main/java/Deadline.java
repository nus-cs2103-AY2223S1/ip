import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private LocalDate time;

    public Deadline(String title, String time, boolean done){
        super(title, "deadline", done);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.time = LocalDate.parse(time, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return (super.toString() + " (by: " + this.time.format(formatter) + ")" );
    }

    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return this.time.format(formatter);
    }
}
