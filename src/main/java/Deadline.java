public class Deadline extends Task {

    private String name;
    private String type;
    private String status;

    private String deadline;

    public Deadline(String name, String deadline) {
        this.name = name;
        this.status = "[ ]";
        this.type = "[D]";
        this.deadline = deadline;
    }

    public void print() {
        System.out.println(
                Duke.line + "\n" +
                        "Got it. I've added this task:" + "\n" +
                        this.type + this.status + " " + this.name + "(by: " + this.deadline + ")"+"\n" +
                        " Now you have " + Duke.count + " tasks in the list." +
                        "\n" + Duke.line + "\n"

        );
    }
    public void list() {
        System.out.println(this.type + this.status + " " + this.name + "(by: " + this.deadline + ")" );
    }
}
