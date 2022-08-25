import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    private String name;
    private String type;
    private String status;

    private LocalDateTime deadline;



    public Deadline(String name, LocalDateTime deadline) {
        this.name = name;
        this.status = "[ ]";
        this.type = "[D]";
        this.deadline = deadline;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void print() {
        System.out.println(
                Duke.line + "\n" +
                        "Got it. I've added this task:" + "\n" +
                        this.type + this.status + " " + this.name + "(by: " + formatDateString(this.deadline) + ")"+"\n" +
                        " Now you have " + Duke.count + " tasks in the list." +
                        "\n" + Duke.line + "\n"

        );
    }
    public void list() {
        System.out.println(this.type + this.status + " " + this.name + "(by: " + formatDateString(this.deadline) + ")" );
    }

    public void mark(Task t, int index) {
        System.out.println(
                Duke.line + "\n" +
                        "Nice! I've marked this task as done:" + "\n" +
                        "[X] " + Duke.list.get(index-1).getName() + "(by: " + formatDateString(this.deadline) + ")" + "\n" + Duke.line
        );
        t.setStatus("[X]");
    }

    public void delete(Task b, int index, ArrayList<Task> list) {
        list.remove(index);
        System.out.println(Duke.line + "\n" + "Noted. I've removed this task:" + "\n" + b.getType()+ b.getStatus()+" " +b.getName()
        + " (by: " + formatDateString(this.deadline) + ")");
        System.out.println("Now you have " + Duke.count+ " tasks in the list." + "\n" + Duke.line);
    };
    public void unmark(Task t, int index) {
        System.out.println(
                Duke.line + "\n" +
                        "OK, I've marked this task as not done yet:" + "\n" +
                        "[ ] " + t.getName() + "(by: " + formatDateString(this.deadline) + ")" + "\n" + Duke.line
        );
        t.setStatus("[ ]");
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

    public LocalDateTime getDeadline() {
        return deadline;
    }


    public String toString() {
        return this.getType() + this.getStatus() + this.getName() + " (by: " + this.deadline + ")" + "\n";
    }


    private String formatDateString (LocalDateTime d) {
            return d.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"));
    }



}
