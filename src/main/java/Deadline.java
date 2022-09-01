import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public Deadline(String desc) {
        super(desc);

        String parts[] = desc.split("/by ");
        super.description = parts[0];
        super.isDone = false;
        super.type = "D";

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
        return "[" + type + "][" + mark + "]" + this.description + "(by: " + formattedDate + ")";
    }
}
