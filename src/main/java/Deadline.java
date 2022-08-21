import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends DatedTask {

    protected LocalDate date;

    public Deadline(String description, String by) throws DateTimeException {
        super(description, LocalDate.parse(by));
        this.date = LocalDate.parse(by);
        //dateFormatter(this.date);
    }

//    public String dateFormatter(LocalDate date) {
//        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
//    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy") )+ ")";
    }
}