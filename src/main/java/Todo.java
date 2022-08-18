public class Todo extends Task {

    public Todo(String desc) {
        super(desc);
        super.isDone = false;
        super.type = "T";
    }
}
