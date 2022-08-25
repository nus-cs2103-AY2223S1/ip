import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    private String name;
    private String type;
    private String status;

    private LocalDateTime time;



    public Deadline(String name, LocalDateTime deadline) {
        this.name = name;
        this.status = "[ ]";
        this.type = "[D]";
        this.time = deadline;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void print() {
        System.out.println(
                Duke.line + "\n" +
                        "Got it. I've added this task:" + "\n" +
                        this.type + this.status + " " + this.name + "(by: " + formatDateString(this.time) + ")"+"\n" +
                        " Now you have " + Duke.count + " tasks in the list." +
                        "\n" + Duke.line + "\n"

        );
    }
    public void list() {
        System.out.println(this.type + this.status + " " + this.name + "(by: " + formatDateString(this.time) + ")" );
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
        return this.getType() + this.getStatus() + this.getName() + " (by: " + formatDateString(this.time) + ")" + "\n";
    }


    private String formatDateString (LocalDateTime d) {
            return d.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }

    public String description() {
        return this.getName() + " (by: " + formatDateString(this.time) + ")";
    }
}
