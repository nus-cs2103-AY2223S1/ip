public class Event extends Task {

    private String name;
    private String type;
    private String status;

    private String time;

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
}
