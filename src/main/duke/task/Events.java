package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Events extends Task{

    private  LocalDate date;
    public Events(String description, String date){
        super(description);
        this.date = LocalDate.parse(date);
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String getDescription() {
        return "[E]" + super.getDescription() + "(" + this.date + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
