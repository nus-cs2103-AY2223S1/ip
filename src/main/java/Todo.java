public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    public String toString() {
        return String.format("[%s]%s", this.getType(), super.toString());
    }

    public String getType() {
        return "T";
    }
}