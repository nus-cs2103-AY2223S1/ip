import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Deadline extends Task {
    String date;

    public Deadline(String description, boolean isDone, String date) {
        super(description.trim());
        this.isDone = isDone;
        this.date = date.trim();
        Task.taskCount++;
    }


    @Override
    public String toString() {
//        LocalDate d = LocalDate.parse(this.date);
//        String formatDate = d.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        return String.format("D | %s | %s | %s", this.getStatusIcon(), this.description, this.date);
    }


}
