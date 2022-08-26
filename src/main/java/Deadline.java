import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private LocalDate date;
    private String time = null;

    public Deadline(String name) {
        super(name);

    }

    @Override
    public String toString() {
        return "[D] " + (super.isCompleted() ? "[X] " : "[ ] ") + super.getTaskName() + "(by: " + this.getDate() + ")";
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + (this.time == null ? "" : ", " + this.getTime());
    }

    public String getTime() {
        if(this.time == null) {
            return "";
        }
        String hh = this.time.substring(0,2);
        String mm = this.time.substring(2,4);

        if (Integer.parseInt(hh) > 12) {
            hh = String.valueOf(Integer.parseInt(hh) - 12);
            return hh + ":" + mm +"pm";
        }
        else {
            if (Integer.parseInt(hh) == 0) {
                return "12:" + mm +"pm";
            } else {
                return hh + ":" + mm +"pm";
            }
        }
    }

}
