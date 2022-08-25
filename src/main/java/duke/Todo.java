package duke;

public class Todo extends Task {

    private String name;
    private String type;
    private String status;

    public Todo(String name) {
        this.name = name;
        this.status = "[ ]";
        this.type = "[T]";
    }

    @Override
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

    public void setStatus(String status) {
        this.status = status;
    }

    public void print() {
        System.out.println(Ui.ADD_TASK_HEADER + this.toString() + " Now you have " + Duke.count +
                " tasks in the list." + "\n" + Duke.line + "\n");
    }

    public void list() {
        System.out.println(this.type + this.status + " " + this.name);
    }

    public String description() {
        return this.getName();
    }
    public String toString() {
        return this.getType() + this.getStatus() + " " + this.getName() + "\n";
    }
}
