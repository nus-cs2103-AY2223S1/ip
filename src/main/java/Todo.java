public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    public String toString() {
        return String.format("[%s]%s", this.getType(), super.toString());
    }

    public String getTime() {
        return "";
    }

    public String getType() {
        return "T";
    }
}