import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private String name;
    private String type;
    private String status;
    private LocalDateTime time;

    public void setStatus(String status) {
        this.status = status;
    }

    public Event(String name, LocalDateTime time) {
        this.name = name;
        this.status = "[ ]";
        this.type = "[E]";
        this.time = time;
    }

    public void print() {
        System.out.println(Ui.ADD_TASK_HEADER + this.toString() + " Now you have " +
                Duke.count + " tasks in the list." + "\n" + Duke.line + "\n");
    }

    public void list() {
        System.out.println(this.type + this.status + " " + this.name + "(at: " + formatDateString(this.time) + ")");
    }

    public String getName() {
        return name;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getType() {
        return type;
    }

    public String toString() {
        return this.getType() + this.getStatus() + this.getName() + " (at: " + formatDateString(this.time) + ")" + "\n";
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    private String formatDateString (LocalDateTime d){
        return d.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }

    @Override
    public String description() {
        return this.getName() + " (at: " + formatDateString(this.time) + ")";
    }
}
