import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadlines extends Task {

    protected String deadline;
    //private LocalDate deadline;

    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    private String deadlineGenerator(String str) {
        String dl = "";
        LocalDate date = LocalDate.parse(str.substring(0, str.length() - 5));
        String month = String.valueOf(date.getMonth());
        String day = String.valueOf(date.getDayOfMonth());
        String year = String.valueOf(date.getYear());

        int time = Integer.parseInt(str.substring(str.length() - 4, str.length()));
        String timeStr = "";
        if (time >= 1300) {
            time -= 1200;
            timeStr = String.valueOf(time) + "pm";
        } else {
            timeStr = String.valueOf(time) + "am";
        }

        dl = day + " " + month + " " + year + ", " + timeStr;

        return dl;
    }


    @Override
    public String toString() {
        //return "[D]" + super.toString() + " (by: " + deadline + ")";
        return "[D]" + super.toString() + " (by: " + deadlineGenerator(deadline) + ")";
    }
}