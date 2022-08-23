import java.time.LocalDate;

public class Deadline extends Task{
    private final LocalDate date;

    public Deadline(String name, String deadline) {
        super(name);
        this.date = Parser.convertStringToDate(deadline);

    }


    @Override
    public String toString() {
        String tag = "[D]";
        return tag + "[" + this.getStatusIcon()  + "] " + this.getTaskName() + "(" + Parser.convertDateToString(date) + ")";
    }
}
