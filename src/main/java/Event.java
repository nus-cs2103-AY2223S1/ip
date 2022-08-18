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
                        "[X] " + Duke.list[index-1].getName() + "(at: " + this.time + ")" + "\n" + Duke.line
        );
        t.setStatus("[X]");
    }

    public void unmark(Task t, int index) {
        System.out.println(
                Duke.line + "\n" +
                        "OK, I've marked this task as not done yet:" + "\n" +
                        "[ ] " + t.getName() +  "(at: " + this.time + ")" + "\n" + Duke.line
        );
        t.setStatus("[ ]");
    }
}
