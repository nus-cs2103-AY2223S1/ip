import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public Event(String desc) {
        super(desc);

        String parts[] = desc.split("/at ");
        super.description = parts[0];
        super.isDone = false;
        super.type = "E";

        String dateInString = parts[1];
        DateFormatter newDate = new DateFormatter(dateInString);
        super.dateAndTime = newDate.formattedDate();
    }

    @Override
    public String getTask() {
        String mark = " ";
        if (this.isDone) {
            mark = "X";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        String formattedDate = dateAndTime.format(formatter);
        return "[" + type + "][" + mark + "]" + this.description + "(at: " + formattedDate + ")";
    }
}
