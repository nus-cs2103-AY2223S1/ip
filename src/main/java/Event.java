import java.util.ArrayList;

public class Event extends Task {

    private String name;
    private String type;
    private String status;

    private String time;

    public void setStatus(String status) {
        this.status = status;
    }

    public Event(String name, String time) {
        this.name = name;
        this.status = "[ ]";
        this.type = "[E]";
        this.time = time;
    }

    public void print() {
        System.out.println(
                Duke.line + "\n" +
                        "Got it. I've added this task:" + "\n" +
                        this.type + this.status + " " + this.name + "(at: " + this.time + ")"+"\n" +
                        " Now you have " + Duke.count + " tasks in the list." +
                        "\n" + Duke.line + "\n"

        );
    }
    public void list() {
        System.out.println(this.type + this.status + " " + this.name + "(at: " + this.time + ")");
    }

    public void mark(Task t, int index) {
        System.out.println(
                Duke.line + "\n" +
                        "Nice! I've marked this task as done:" + "\n" +
                        "[X] " + t.getName() + "(at: " + this.time + ")" + "\n" + Duke.line
        );
        t.setStatus("[X]");
    }

    public void delete(Task b, int index, ArrayList<Task> list) {
        list.remove(index);
        System.out.println(Duke.line + "\n" + "Noted. I've removed this task:" + "\n" + b.getType()+b.getStatus()+" " +b.getName()
                + " (at: " + this.time + ")");
        System.out.println("Now you have " + Duke.count+ " tasks in the list." + "\n" + Duke.line);
    };

    public void unmark(Task t, int index) {
        System.out.println(
                Duke.line + "\n" +
                        "OK, I've marked this task as not done yet:" + "\n" +
                        "[ ] " + t.getName() +  "(at: " + this.time + ")" + "\n" + Duke.line
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
}
