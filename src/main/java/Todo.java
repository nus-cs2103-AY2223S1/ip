public class Todo extends Task {

    private String name;
    private String type;
    private String status;

    public Todo(String name) {
        this.name = name;
        this.status = "[ ]";
        this.type = "[T]";
    }

    public void unmark(Task t, int index) {
        System.out.println(
                Duke.line + "\n" +
                        "OK, I've marked this task as not done yet:" + "\n" +
                        "[ ] " + t.getName() + "\n" + Duke.line
        );
        t.getStatus() = "[ ]";
    }



    public void print() {
        System.out.println(
                Duke.line + "\n" +
                        "Got it. I've added this task:" + "\n" +
                        this.type + this.status + " " + this.name + "\n" +
                       " Now you have " + Duke.count + " tasks in the list." +
                        "\n" + Duke.line + "\n"

        );
    }

    public void list() {
        System.out.println(this.type + this.status + " " + this.name  );
    }
}
